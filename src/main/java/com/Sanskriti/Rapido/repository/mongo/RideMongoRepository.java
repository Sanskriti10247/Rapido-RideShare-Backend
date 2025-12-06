package com.Sanskriti.Rapido.repository.mongo;

import com.Sanskriti.Rapido.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideMongoRepository extends MongoRepository<Ride, String> {
}
