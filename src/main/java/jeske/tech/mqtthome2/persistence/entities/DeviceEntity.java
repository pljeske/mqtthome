package jeske.tech.mqtthome2.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Peer-Lucas Jeske
 * created 16.11.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeviceEntity {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String topic;
    @ElementCollection
    private Set<String> possibleStates;
}
