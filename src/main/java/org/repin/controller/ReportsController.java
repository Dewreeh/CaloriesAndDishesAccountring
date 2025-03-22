package org.repin.controller;

import org.repin.repository.DishRepository;
import org.repin.repository.MealIntakeDishRepository;
import org.repin.repository.MealIntakeRepository;
import org.repin.repository.UserRepository;
import org.repin.service.CaloriesService;
import org.repin.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/get")
public class ReportsController {

    private final ReportsService reportsService;
    @Autowired
    ReportsController(ReportsService reportsService){
        this.reportsService = reportsService;
    }

    @GetMapping("/daily_report/{userId}")
    ResponseEntity<Object> getDailyReport(@PathVariable(name = "userId") UUID userId){
        return ResponseEntity.ok().body(reportsService.getDailyReport(userId));
    }
}
