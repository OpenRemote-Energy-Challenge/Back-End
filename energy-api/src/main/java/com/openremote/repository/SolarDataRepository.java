package com.openremote.repository;

import com.openremote.model.SolarData;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface SolarDataRepository extends MongoRepository<SolarData, UUID> {

}
