package com.Sanskriti.Rapido.service;

import com.Sanskriti.Rapido.model.Ride;
import com.Sanskriti.Rapido.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride createRide(Ride ride, String userId) {

        ride.setUserId(userId);
        ride.setStatus("REQUESTED");
        ride.setCreatedAt(new Date());

        return rideRepository.save(ride);
    }

    public List<Ride> getPendingRides() {
        return rideRepository.findByStatus("REQUESTED");
    }


    public Ride acceptRide(String rideId, String driverId) {

        Ride ride = rideRepository.findById(rideId);

        if (ride == null) {
            throw new RuntimeException("Ride not found");
        }

        if (!ride.getStatus().equals("REQUESTED")) {
            throw new RuntimeException("Ride already accepted or completed");
        }

        ride.setDriverId(driverId);
        ride.setStatus("ACCEPTED");

        return rideRepository.save(ride);
    }

    public Ride completeRide(String rideId) {

        Ride ride = rideRepository.findById(rideId);

        if (ride == null) {
            throw new RuntimeException("Ride not found");
        }

        ride.setStatus("COMPLETED");

        return rideRepository.save(ride);
    }

    public List<Ride> getUserRides(String userId) {
        return rideRepository.findByUserId(userId);
    }
}
