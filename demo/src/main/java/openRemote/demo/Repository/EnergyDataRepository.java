package openRemote.demo.Repository;


import openRemote.demo.Model.EnergyData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnergyDataRepository extends MongoRepository<EnergyData, ObjectId> {
}
