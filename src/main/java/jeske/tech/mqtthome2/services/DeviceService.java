package jeske.tech.mqtthome2.services;

import jeske.tech.mqtthome2.model.Device;
import jeske.tech.mqtthome2.persistence.repo.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Peer-Lucas Jeske
 * created 16.11.2022
 */
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void add(Device device) {
        deviceRepository.save(device.toEntity());
    }

    public List<Device> getAll() {
        return deviceRepository.findAll().stream().map(Device::new).toList();
    }

    public Device get(Long id) {
        return new Device(deviceRepository.findById(id).orElseThrow());
    }
}
