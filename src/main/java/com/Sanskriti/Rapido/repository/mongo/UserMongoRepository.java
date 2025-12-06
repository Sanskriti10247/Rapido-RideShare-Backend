package com.Sanskriti.Rapido.repository.mongo;

import com.Sanskriti.Rapido.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User, String> {
}
