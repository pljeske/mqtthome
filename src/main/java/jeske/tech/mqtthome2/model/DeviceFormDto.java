package jeske.tech.mqtthome2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Peer-Lucas Jeske
 * created 16.11.2022
 */
@Getter
@Setter
@NoArgsConstructor
public class DeviceFormDto {
    private String name;
    private String topic;
    private String possibleStatesString;
}
