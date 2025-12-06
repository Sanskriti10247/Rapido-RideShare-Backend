package com.Sanskriti.Rapido.controller;

import com.Sanskriti.Rapido.model.Ride;
import com.Sanskriti.Rapido.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/rides")
    public Ride createRide(@RequestBody Ride ride, Principal principal) {
        return rideService.createRide(ride, principal.getName());
    }

    @GetMapping("/driver/rides/requests")
    public List<Ride> getPendingRides() {
        return rideService.getPendingRides();
    }

    @PostMapping("/driver/rides/{rideId}/accept")
    public Ride acceptRide(@PathVariable String rideId,
                           Principal principal) {

        return rideService.acceptRide(rideId, principal.getName());
    }

    @PostMapping("/rides/{rideId}/complete")
    public Ride completeRide(@PathVariable String rideId) {
        return rideService.completeRide(rideId);
    }

    @GetMapping("/user/rides")
    public List<Ride> getUserRides(Principal principal) {
        return rideService.getUserRides(principal.getName());
    }
}
