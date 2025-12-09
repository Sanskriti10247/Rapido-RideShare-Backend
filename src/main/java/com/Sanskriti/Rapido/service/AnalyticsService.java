package com.Sanskriti.Rapido.service;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class AnalyticsService {

    private final MongoTemplate mongoTemplate;

    public AnalyticsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // CLASSROOM STEP 6 — Driver Total Earnings
    public Double totalEarnings(String driverId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("driverId").is(driverId)
                        .and("status").is("COMPLETED")),
                group().sum("fare").as("total")
        );

        Document result = mongoTemplate
                .aggregate(agg, "rides", Document.class)
                .getUniqueMappedResult();

        return result != null ? result.getDouble("total") : 0.0;
    }

    //  API 10 — Rides per day
    public List<Document> ridesPerDay() {
        Aggregation agg = newAggregation(
                group("createdAt").count().as("totalRides"),
                sort(Sort.Direction.ASC, "_id")
        );

        return mongoTemplate
                .aggregate(agg, "rides", Document.class)
                .getMappedResults();
    }

    //  API 11 — Driver Summary
    public Document driverSummary(String driverId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("driverId").is(driverId)),
                group("driverId")
                        .count().as("totalRides")
                        .avg("distanceKm").as("avgDistance")
                        .sum("fare").as("totalFare"),
                project("totalRides", "avgDistance", "totalFare")
                        .and("_id").as("driverId")
        );

        return mongoTemplate
                .aggregate(agg, "rides", Document.class)
                .getUniqueMappedResult();
    }

    //  API 12 — User Spending
    public Document userSpending(String userId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("userId").is(userId)
                        .and("status").is("COMPLETED")),
                group("userId")
                        .count().as("totalRides")
                        .sum("fare").as("totalSpent"),
                project("totalRides", "totalSpent")
                        .and("_id").as("userId")
        );

        return mongoTemplate
                .aggregate(agg, "rides", Document.class)
                .getUniqueMappedResult();
    }

    //  API 13 — Status Summary
    public List<Document> statusSummary() {
        Aggregation agg = newAggregation(
                group("status").count().as("count")
        );

        return mongoTemplate
                .aggregate(agg, "rides", Document.class)
                .getMappedResults();
    }
}
