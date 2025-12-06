package com.Sanskriti.Rapido.repository;

import com.Sanskriti.Rapido.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RideRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Ride> findAll() {
        return mongoTemplate.findAll(Ride.class);
    }


    public Ride findById(String id) {
        return mongoTemplate.findById(id, Ride.class);
    }


    public Ride save(Ride ride) {
        return mongoTemplate.save(ride);
    }


    public boolean deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, Ride.class).getDeletedCount() > 0;
    }

    public List<Ride> findByStatus(String status) {
        Query query = Query.query(Criteria.where("status").is(status));
        return mongoTemplate.find(query, Ride.class);
    }


    public List<Ride> findByUserId(String userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Ride.class);
    }
}
