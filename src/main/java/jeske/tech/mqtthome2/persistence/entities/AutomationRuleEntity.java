package jeske.tech.mqtthome2.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Peer-Lucas Jeske
 * created 10.11.2022
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutomationRuleEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String listenTopic;
    private String listenState;
    private String actionTopic;
    private String actionState;
    @ManyToOne
    private DeviceEntity listenDevice;
    @ManyToOne
    private DeviceEntity actionDevice;
}
