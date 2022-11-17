package jeske.tech.mqtthome2.persistence.repo;

import jeske.tech.mqtthome2.persistence.entities.AutomationRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Peer-Lucas Jeske
 * created 10.11.2022
 */
@Repository
public interface AutomationRuleRepository extends JpaRepository<AutomationRuleEntity, Long> {
    List<AutomationRuleEntity> findAllByListenTopic(String topic);
}
