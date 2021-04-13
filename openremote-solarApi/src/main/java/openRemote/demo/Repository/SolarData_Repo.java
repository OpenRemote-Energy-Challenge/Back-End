package openRemote.demo.Repository;

import openRemote.demo.Model.SolarData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SolarData_Repo extends MongoRepository<SolarData, ObjectId> {

}
