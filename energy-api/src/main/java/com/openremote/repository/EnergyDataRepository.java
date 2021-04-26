package com.openremote.repository;

import com.openremote.model.EnergyData;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface EnergyDataRepository extends MongoRepository<EnergyData, UUID> {
}
