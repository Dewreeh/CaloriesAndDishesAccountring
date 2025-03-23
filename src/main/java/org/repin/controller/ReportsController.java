package org.repin.controller;
import org.repin.repository.UserRepository;
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

    @GetMapping("/report/daily/{userId}")
    ResponseEntity<Object> getDailyReport(@PathVariable(name = "userId") UUID userId,
                                          @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date){

        return ResponseEntity.ok().body(reportsService.getDailyReport(userId, date));
    }

    @GetMapping("/report/is_limit_kept/{userId}")
    ResponseEntity<Object> isDailyLimitKept(@PathVariable(name = "userId") UUID userId,
                                          @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date){
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }

        return ResponseEntity.ok().body(reportsService.isDailyLimitKept(userId, date));
    }

    @GetMapping("/report/nutrition_history/{userId}")
    ResponseEntity<Object> getNutritionHistory(@PathVariable(name = "userId") UUID userId,
                                            @RequestParam(name = "start") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate start,
                                            @RequestParam(name = "end") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate end){
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }

        return ResponseEntity.ok().body(reportsService.getNutritionHistory(userId, start, end));
    }
}
