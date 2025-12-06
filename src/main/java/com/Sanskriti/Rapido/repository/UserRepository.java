package com.Sanskriti.Rapido.repository;

import com.Sanskriti.Rapido.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User save(User user) {
        return mongoTemplate.save(user);
    }

    public User findById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    public User findByUsername(String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class);
    }


    public boolean deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, User.class).getDeletedCount() > 0;
    }
}
