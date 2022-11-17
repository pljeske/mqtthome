package jeske.tech.mqtthome2.controllers;

import jeske.tech.mqtthome2.model.Device;
import jeske.tech.mqtthome2.services.DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Peer-Lucas Jeske
 * created 16.11.2022
 */
@RestController
@RequestMapping("/api")
public class JsRestController {
    private final DeviceService deviceService;

    public JsRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices")
    public List<Device> getDevices() {
        return deviceService.getAll();
    }

    @GetMapping("/devices/{deviceId}")
    public Device getDevice(@PathVariable Long deviceId) {
        return deviceService.get(deviceId);
    }
}
