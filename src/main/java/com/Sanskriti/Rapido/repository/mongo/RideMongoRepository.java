package com.Sanskriti.Rapido.repository.mongo;

import com.Sanskriti.Rapido.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface RideMongoRepository extends MongoRepository<Ride, String> {

    // ✅ API 1 — Search pickup OR drop (Regex + Case Insensitive) — FIXED VERSION
    @Query("{ '$or': [ " +
            "{ 'pickupLocation': { $regex: ?0, $options: 'i' } }, " +
            "{ 'dropLocation':   { $regex: ?0, $options: 'i' } } " +
            "] }")
    List<Ride> searchByPickupOrDrop(String text);

    // ✅ API 2 — Distance range filter
    List<Ride> findByDistanceKmBetween(Double min, Double max);

    // ✅ API 5 — All rides for a user
    List<Ride> findByUserId(String userId);

    // ✅ API 6 — User rides by status
    List<Ride> findByUserIdAndStatus(String userId, String status);

    // ✅ API 7 — Driver active rides (ACCEPTED)
    List<Ride> findByDriverIdAndStatus(String driverId, String status);

    // ✅ API 3 & API 14 — Date range / specific date
    List<Ride> findByCreatedAtBetween(Date start, Date end);
}
