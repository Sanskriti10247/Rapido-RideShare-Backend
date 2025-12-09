package com.Sanskriti.Rapido.controller;

import com.Sanskriti.Rapido.model.Ride;
import com.Sanskriti.Rapido.repository.mongo.RideMongoRepository;
import com.Sanskriti.Rapido.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private RideMongoRepository rideMongoRepository;

    // User creates a ride
    @PostMapping("/rides")
    public Ride createRide(@RequestBody Ride ride, Principal principal) {
        return rideService.createRide(ride, principal.getName());
    }

    // Driver views pending ride requests
    @GetMapping("/driver/rides/requests")
    public List<Ride> getPendingRides() {
        return rideService.getPendingRides();
    }

    // Driver accepts a ride
    @PostMapping("/driver/rides/{rideId}/accept")
    public Ride acceptRide(@PathVariable String rideId,
                           Principal principal) {
        return rideService.acceptRide(rideId, principal.getName());
    }

    // Ride completion
    @PostMapping("/rides/{rideId}/complete")
    public Ride completeRide(@PathVariable String rideId) {
        return rideService.completeRide(rideId);
    }

    // User ride history
    @GetMapping("/user/rides")
    public List<Ride> getUserRides(Principal principal) {
        return rideService.getUserRides(principal.getName());
    }

    // =====================================================
    //  PART-2 CLASSROOM SEARCH / FILTER / QUERY APIs
    // =====================================================

    // API 1 — Search pickup OR drop by keyword

    @GetMapping("/rides/search")
    public List<Ride> searchRides(@RequestParam String text) {
        return rideMongoRepository.searchByPickupOrDrop(text);
    }

    // API 2 — Filter rides by distance range
    @GetMapping("/rides/filter-distance")
    public List<Ride> filterByDistance(@RequestParam Double min,
                                       @RequestParam Double max) {
        return rideMongoRepository.findByDistanceKmBetween(min, max);
    }

    // API 3 — Filter rides between date range
    @GetMapping("/rides/filter-date-range")
    public List<Ride> filterByDateRange(@RequestParam String start,
                                        @RequestParam String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            return rideMongoRepository.findByCreatedAtBetween(startDate, endDate);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format. Use yyyy-MM-dd");
        }
    }

    // API 4 — Sort rides by fare
    @GetMapping("/rides/sort")
    public List<Ride> sortByFare(@RequestParam(defaultValue = "asc") String order) {
        if ("desc".equalsIgnoreCase(order)) {
            return rideMongoRepository.findAll(
                    Sort.by("fare").descending()
            );
        }
        return rideMongoRepository.findAll(
                Sort.by("fare").ascending()
        );
    }

    // API 5 — Get all rides for a user
    @GetMapping("/rides/user/{userId}")
    public List<Ride> ridesForUser(@PathVariable String userId) {
        return rideMongoRepository.findByUserId(userId);
    }

    // API 6 — Get rides for a user by status
    @GetMapping("/rides/user/{userId}/status/{status}")
    public List<Ride> ridesForUserByStatus(@PathVariable String userId,
                                           @PathVariable String status) {
        return rideMongoRepository.findByUserIdAndStatus(userId, status);
    }

    // API 7 — Driver's active rides (ACCEPTED)
    @GetMapping("/driver/{driverId}/active-rides")
    public List<Ride> activeRidesForDriver(@PathVariable String driverId) {
        return rideMongoRepository.findByDriverIdAndStatus(driverId, "ACCEPTED");
    }

    // API 8 — Status + keyword search
    @GetMapping("/rides/filter-status")
    public List<Ride> filterByStatusAndKeyword(@RequestParam String status,
                                               @RequestParam String search) {

        String keyword = search.toLowerCase();

        return rideMongoRepository.findAll().stream()
                .filter(r -> r.getStatus().equalsIgnoreCase(status))
                .filter(r ->
                        (r.getPickupLocation() != null &&
                                r.getPickupLocation().toLowerCase().contains(keyword))
                                ||
                                (r.getDropLocation() != null &&
                                        r.getDropLocation().toLowerCase().contains(keyword))
                )
                .toList();
    }

    // API 9 — Advanced search (pagination by status)
    @GetMapping("/rides/advanced-search")
    public List<Ride> advancedSearch(@RequestParam String status,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {

        List<Ride> filtered = rideMongoRepository.findAll().stream()
                .filter(r -> r.getStatus().equalsIgnoreCase(status))
                .toList();

        int from = page * size;
        int to = Math.min(from + size, filtered.size());

        if (from >= filtered.size()) {
            return List.of();
        }
        return filtered.subList(from, to);
    }

    // API 14 — Rides on a specific date
    @GetMapping("/rides/date/{date}")
    public List<Ride> ridesOnDate(@PathVariable String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(date);
            return rideMongoRepository.findByCreatedAtBetween(d, d);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format. Use yyyy-MM-dd");
        }
    }
}
