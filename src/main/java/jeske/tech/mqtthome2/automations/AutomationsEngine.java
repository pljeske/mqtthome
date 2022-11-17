package jeske.tech.mqtthome2.automations;

import jeske.tech.mqtthome2.config.SpringIntegrationConfig;
import jeske.tech.mqtthome2.model.AutomationRule;
import jeske.tech.mqtthome2.services.AutomationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Peer-Lucas Jeske
 * created 15.11.2022
 */
@Service
public class AutomationsEngine {
    private static final Logger LOG = LoggerFactory.getLogger(AutomationsEngine.class);
    private final AutomationsService service;
    private final SpringIntegrationConfig.MqttGateway mqttGateway;
    private final MqttPahoMessageDrivenChannelAdapter messageAdapter;
    private final Map<String, Set<AutomationRule>> activeAutomations;

    public AutomationsEngine(AutomationsService service, SpringIntegrationConfig.MqttGateway mqttGateway,
                             MqttPahoMessageDrivenChannelAdapter messageAdapter) {
        this.service = service;
        this.activeAutomations = new HashMap<>();
        this.mqttGateway = mqttGateway;
        this.messageAdapter = messageAdapter;
    }

    public final void init() {
        List<AutomationRule> all = service.getAll();
        all.forEach((AutomationRule automationRule) -> {
            Set<AutomationRule> rules = activeAutomations
                    .computeIfAbsent(automationRule.getListenTopic(), k -> new HashSet<>());
            rules.add(automationRule);
        });
        if (!activeAutomations.keySet().isEmpty()) {
            messageAdapter.addTopic(activeAutomations.keySet().toArray(String[]::new));
        }
    }

    public final void reloadAutomations() {
        List<AutomationRule> all = service.getAll();
        if (!all.isEmpty()) {
            messageAdapter.removeTopic(activeAutomations.keySet().toArray(String[]::new));
        }
        activeAutomations.clear();
        all.forEach((AutomationRule automationRule) -> {
            Set<AutomationRule> rules = activeAutomations
                    .computeIfAbsent(automationRule.getListenTopic(), k -> new HashSet<>());
            rules.add(automationRule);
        });
        if (!activeAutomations.keySet().isEmpty()) {
            messageAdapter.addTopic(activeAutomations.keySet().toArray(String[]::new));
        }
    }

    public void handleTopicAndState(String topic, String state) {
        LOG.info("Handling topic {} with state {}", topic, state);
        Set<AutomationRule> applicableAutomations = activeAutomations.getOrDefault(topic, Collections.emptySet());
        applicableAutomations.forEach((AutomationRule rule) -> {
            if (rule.getListenState().equals(state)) {
                String actionTopic = rule.getActionTopic();
                String actionState = rule.getActionState();

                LOG.info("Publishing topic={} state={}", actionTopic, actionState);
                Map<String, Object> headers = new HashMap<>();
                headers.put("mqtt_topic", actionTopic);
                headers.put("mqtt_retained", false);
                headers.put("mqtt_qos", 1);
                mqttGateway.sendToMqtt(actionState, headers);
            }
        });
    }
}
