package openRemote.demo.Repository;

import openRemote.demo.Model.SolarData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarData_Repo extends MongoRepository<SolarData, ObjectId> {

}
