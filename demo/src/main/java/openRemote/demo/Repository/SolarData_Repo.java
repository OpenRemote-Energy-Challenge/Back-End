package openRemote.demo.Repository;

import openRemote.demo.Model.SolarData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SolarData_Repo extends MongoRepository<SolarData, UUID> {

}
