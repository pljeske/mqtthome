package jeske.tech.mqtthome2.persistence.repo;

import jeske.tech.mqtthome2.persistence.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long>  {
}
