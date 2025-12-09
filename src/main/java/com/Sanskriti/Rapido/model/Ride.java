//package com.Sanskriti.Rapido.model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.Date;
//
//    @Document(collection = "rides")
//    public class Ride {
//
//        @Id
//        private String id;
//
//        private String userId;     // Passenger (FK)
//        private String driverId;   // Driver (FK)
//
//        private String pickupLocation;
//        private String dropLocation;
//
//        private String status;    // REQUESTED / ACCEPTED / COMPLETED
//        private Date createdAt;
//
//
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getUserId() {
//            return userId;
//        }
//
//        public void setUserId(String userId) {
//            this.userId = userId;
//        }
//
//        public String getDriverId() {
//            return driverId;
//        }
//
//        public void setDriverId(String driverId) {
//            this.driverId = driverId;
//        }
//
//        public String getPickupLocation() {
//            return pickupLocation;
//        }
//
//        public void setPickupLocation(String pickupLocation) {
//            this.pickupLocation = pickupLocation;
//        }
//
//        public String getDropLocation() {
//            return dropLocation;
//        }
//
//        public void setDropLocation(String dropLocation) {
//            this.dropLocation = dropLocation;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public Date getCreatedAt() {
//            return createdAt;
//        }
//
//        public void setCreatedAt(Date createdAt) {
//            this.createdAt = createdAt;
//        }
//    }
//
//




package com.Sanskriti.Rapido.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "rides")
public class Ride {

    @Id
    private String id;

    // Passenger and Driver
    private String userId;     // Passenger ID
    private String driverId;   // Driver ID

    // Locations
    private String pickupLocation;
    private String dropLocation;

    // NEW FIELDS for analytics / filters
    private Double fare;          // used for sorting + totals
    private Double distanceKm;    // used for distance filters

    // Status + time
    // REQUESTED / ACCEPTED / COMPLETED
    private String status;
    private Date createdAt;       // when the ride was created

    // -------- GETTERS & SETTERS --------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
