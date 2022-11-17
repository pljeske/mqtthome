package jeske.tech.mqtthome2.services;

import jeske.tech.mqtthome2.model.AutomationRule;
import jeske.tech.mqtthome2.persistence.entities.AutomationRuleEntity;
import jeske.tech.mqtthome2.persistence.entities.DeviceEntity;
import jeske.tech.mqtthome2.persistence.repo.AutomationRuleRepository;
import jeske.tech.mqtthome2.persistence.repo.DeviceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Peer-Lucas Jeske
 * created 15.11.2022
 */
@Service
public class AutomationsService {
    private final AutomationRuleRepository repository;
    private final DeviceRepository deviceRepository;

    public AutomationsService(AutomationRuleRepository repository, DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        this.repository = repository;
    }

    public void add(AutomationRule rule) {
        DeviceEntity listenDevice = deviceRepository.findById(rule.getListenDevice().getId()).orElse(null);
        DeviceEntity actionDevice = deviceRepository.findById(rule.getActionDevice().getId()).orElse(null);
        AutomationRuleEntity entity = rule.toEntity();
        entity.setActionDevice(actionDevice);
        entity.setListenDevice(listenDevice);
        repository.save(entity);
    }

    public void remove(AutomationRule rule) {
        repository.deleteById(rule.getId());
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public AutomationRule get(Long id) {
        return new AutomationRule(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AutomationRule with id " + id + " not found")));
    }

    public List<AutomationRule> getAllForTopic(String topic) {
        return repository.findAllByListenTopic(topic).stream().map(AutomationRule::new).toList();
    }

    public List<AutomationRule> getAll() {
        return repository.findAll().stream().map(AutomationRule::new).toList();
    }
}
