package openRemote.demo.Repository;


import openRemote.demo.Model.EnergyData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyDataRepository extends MongoRepository<EnergyData, ObjectId> {
}
