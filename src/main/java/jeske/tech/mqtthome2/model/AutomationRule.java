package jeske.tech.mqtthome2.model;

import jeske.tech.mqtthome2.persistence.entities.AutomationRuleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Peer-Lucas Jeske
 * created 10.11.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutomationRule {
    private Long id;
    private String listenTopic;
    private String listenState;
    private String actionTopic;
    private String actionState;

    private Device listenDevice;
    private Device actionDevice;

    public AutomationRule(AutomationRuleEntity entity) {
        this.id = entity.getId();
        this.listenTopic = entity.getListenTopic();
        this.listenState = entity.getListenState();
        this.actionTopic = entity.getActionTopic();
        this.actionState = entity.getActionState();
        this.actionDevice = new Device(entity.getActionDevice());
        this.listenDevice = new Device(entity.getListenDevice());
    }

    public AutomationRuleEntity toEntity() {
        AutomationRuleEntity entity = new AutomationRuleEntity();
        entity.setId(this.id);
        entity.setListenTopic(this.listenTopic);
        entity.setListenState(this.listenState);
        entity.setActionTopic(this.actionTopic);
        entity.setActionState(this.actionState);
        return entity;
    }
}
