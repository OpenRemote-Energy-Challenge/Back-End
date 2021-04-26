package openRemote.demo.Repository;


import openRemote.demo.Model.EnergyData;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface EnergyDataRepository extends MongoRepository<EnergyData, UUID> {
}
