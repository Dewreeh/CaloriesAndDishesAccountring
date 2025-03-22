package org.repin.controller;

import org.repin.model.User;
import org.repin.repository.DishRepository;
import org.repin.repository.MealIntakeDishRepository;
import org.repin.repository.MealIntakeRepository;
import org.repin.repository.UserRepository;
import org.repin.service.CaloriesService;
import org.repin.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/get")
public class ReportsController {

    private final ReportsService reportsService;
    private final UserRepository userRepository;
    @Autowired
    ReportsController(ReportsService reportsService,
                      UserRepository userRepository){
        this.reportsService = reportsService;
        this.userRepository = userRepository;
    }

    @GetMapping("/daily_report/{userId}")
    ResponseEntity<Object> getDailyReport(@PathVariable(name = "userId") UUID userId,
                                          @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date){
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }

        return ResponseEntity.ok().body(reportsService.getDailyReport(userId, date));
    }
}
