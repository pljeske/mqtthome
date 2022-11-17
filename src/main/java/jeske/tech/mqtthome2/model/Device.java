package jeske.tech.mqtthome2.model;

import jeske.tech.mqtthome2.persistence.entities.DeviceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author Peer-Lucas Jeske
 * created 16.11.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private Long id;
    private String name;
    private String topic;
    private Set<String> possibleStates;

    public Device(DeviceEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.topic = entity.getTopic();
        this.possibleStates = entity.getPossibleStates();
    }

    public DeviceEntity toEntity() {
        DeviceEntity entity = new DeviceEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setTopic(this.topic);
        entity.setPossibleStates(this.possibleStates);
        return entity;
    }
}
