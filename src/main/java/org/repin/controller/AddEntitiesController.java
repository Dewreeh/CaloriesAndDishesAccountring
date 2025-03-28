package org.repin.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.repin.dto.NewDishDto;
import org.repin.dto.NewMealIntakeDto;
import org.repin.dto.NewUserDto;
import org.repin.model.Dish;
import org.repin.model.User;
import org.repin.repository.DishRepository;
import org.repin.repository.UserRepository;
import org.repin.service.CaloriesService;
import org.repin.service.MealIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
@Slf4j
public class AddEntitiesController {
    private final UserRepository userRepository;
    private final CaloriesService caloriesService;
    private final DishRepository dishRepository;
    private final MealIntakeService mealIntakeService;
    @Autowired
    AddEntitiesController(UserRepository userRepository,
                   CaloriesService caloriesService,
                   DishRepository dishRepository,
                   MealIntakeService mealIntakeService){
        this.userRepository = userRepository;
        this.caloriesService = caloriesService;
        this.dishRepository = dishRepository;
        this.mealIntakeService = mealIntakeService;

    }

    @PostMapping("/user")
    ResponseEntity<Object> addUser(@RequestBody @Valid NewUserDto dto){
        log.info("/add_user: {}", dto);

        User user = new User(dto.getName(),
                dto.getEmail(),
                dto.getAge(),
                dto.getWeight(),
                dto.getHeight(),
                dto.getGoal(),
                caloriesService.countDailyCaloriesLimit(dto));

        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @PostMapping("/dish")
    ResponseEntity<Object> addDish(@RequestBody @Valid NewDishDto dto) {
        log.info("/add_dish: {}", dto);

        Dish dish = new Dish(
                dto.getName(),
                dto.getCalories(),
                dto.getProteins(),
                dto.getFats(),
                dto.getCarbohydrates()
        );

        return ResponseEntity.ok().body(dishRepository.save(dish));
    }

    @PostMapping("/meal_intake")
    ResponseEntity<Object> addMealIntake(@RequestBody @Valid NewMealIntakeDto dto) {
        log.info("/add_meal_intake: {}", dto);

        return ResponseEntity.ok().body(mealIntakeService.addMealIntake(dto));
    }

}
