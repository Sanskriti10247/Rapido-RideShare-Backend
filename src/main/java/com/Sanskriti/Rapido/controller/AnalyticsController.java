package com.Sanskriti.Rapido.controller;

import com.Sanskriti.Rapido.service.AnalyticsService;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // ✅ API 10 — Rides per day
    @GetMapping("/rides-per-day")
    public List<Document> ridesPerDay() {
        return analyticsService.ridesPerDay();
    }

    // ✅ API 11 — Driver summary
    @GetMapping("/driver/{driverId}/summary")
    public Document driverSummary(@PathVariable String driverId) {
        return analyticsService.driverSummary(driverId);
    }

    // ✅ API 12 — User spending
    @GetMapping("/user/{userId}/spending")
    public Document userSpending(@PathVariable String userId) {
        return analyticsService.userSpending(userId);
    }

    // ✅ API 13 — Status summary
    @GetMapping("/status-summary")
    public List<Document> statusSummary() {
        return analyticsService.statusSummary();
    }

    // ✅ From classroom — Driver earnings
    @GetMapping("/driver/{driverId}/earnings")
    public Double earnings(@PathVariable String driverId) {
        return analyticsService.totalEarnings(driverId);
    }
}
