package jeske.tech.mqtthome2;

import jeske.tech.mqtthome2.automations.AutomationsEngine;
import jeske.tech.mqtthome2.config.MqttAutomationsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MqttAutomationsConfig.class)
public class MqttHome2Application implements CommandLineRunner {
    @Autowired
    private AutomationsEngine automationsEngine;

    public static void main(String[] args) {
        SpringApplication.run(MqttHome2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        automationsEngine.init();
    }
}
