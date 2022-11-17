package jeske.tech.mqtthome2.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Peer-Lucas Jeske
 * created 15.11.2022
 */
@ConfigurationProperties("mqtt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MqttAutomationsConfig {
    private String brokerUrl;
    private String clientId;
    private String username;
    private String password;
    private int qos;
}

