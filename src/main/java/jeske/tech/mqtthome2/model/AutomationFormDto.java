package jeske.tech.mqtthome2.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AutomationFormDto {
    private Long listenDeviceId;
    private String listenState;
    private Long actionDeviceId;
    private String actionState;
}
