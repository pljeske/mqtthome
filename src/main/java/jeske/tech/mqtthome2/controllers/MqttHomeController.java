package jeske.tech.mqtthome2.controllers;

import jeske.tech.mqtthome2.automations.AutomationsEngine;
import jeske.tech.mqtthome2.model.AutomationFormDto;
import jeske.tech.mqtthome2.model.AutomationRule;
import jeske.tech.mqtthome2.model.Device;
import jeske.tech.mqtthome2.model.DeviceFormDto;
import jeske.tech.mqtthome2.services.AutomationsService;
import jeske.tech.mqtthome2.services.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Peer-Lucas Jeske
 * created 10.11.2022
 */
@Controller
@RequestMapping("/")
public class MqttHomeController {
    private final AutomationsService automationsService;
    private final DeviceService deviceService;
    private final AutomationsEngine engine;

    public MqttHomeController(AutomationsService automationsService, DeviceService deviceService,
                              AutomationsEngine engine) {
        this.automationsService = automationsService;
        this.deviceService = deviceService;
        this.engine = engine;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("devices", deviceService.getAll());
        model.addAttribute("automations", automationsService.getAll());
        return "home";
    }

    @GetMapping("/automations")
    public String getAutomations(Model model, @RequestParam(required = false, defaultValue = "false") Boolean reload) {
        if (Boolean.TRUE.equals(reload)) {
            engine.reloadAutomations();
        }
        model.addAttribute("automations", automationsService.getAll());
        return "automations";
    }

    @GetMapping("/automations/{automationId}/delete")
    public String deleteAutomation(@PathVariable Long automationId) {
        automationsService.remove(automationId);
        return "redirect:/automations";
    }

    @PostMapping("/automation")
    public String addAutomation(AutomationRule automationRule) {
        deviceService.getAll().stream().filter(device -> device.getTopic().equals(automationRule.getListenTopic()))
                .findFirst().ifPresent(automationRule::setListenDevice);
        deviceService.getAll().stream().filter(device -> device.getTopic().equals(automationRule.getActionTopic()))
                .findFirst().ifPresent(automationRule::setActionDevice);
        automationsService.add(automationRule);
        engine.reloadAutomations();
        return "redirect:/";
    }

    @PostMapping("/deviceautomation")
    public String addDeviceAutomation(AutomationFormDto formDto) {
        AutomationRule rule = new AutomationRule();
        Device actionDevice = deviceService.get(formDto.getActionDeviceId());
        Device listenDevice = deviceService.get(formDto.getListenDeviceId());
        rule.setListenState(formDto.getListenState());
        rule.setActionState(formDto.getActionState());
        rule.setListenTopic(listenDevice.getTopic());
        rule.setActionTopic(actionDevice.getTopic());
        rule.setActionDevice(actionDevice);
        rule.setListenDevice(listenDevice);
        automationsService.add(rule);
        engine.reloadAutomations();
        return "redirect:/devices";
    }

    @GetMapping("/devices")
    public String getDevices(Model model) {
        model.addAttribute("devices", deviceService.getAll());
        return "devices";
    }

    @PostMapping("/devices")
    public String addDevice(DeviceFormDto deviceFormDto) {
        Device device = new Device();
        device.setName(deviceFormDto.getName());
        device.setTopic(deviceFormDto.getTopic());

        device.setPossibleStates(Arrays.stream(deviceFormDto.getPossibleStatesString().split(","))
                .collect(Collectors.toSet()));
        deviceService.add(device);
        return "redirect:/";
    }
}
