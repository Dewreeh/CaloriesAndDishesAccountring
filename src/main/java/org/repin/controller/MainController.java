package org.repin.controller;

import lombok.extern.slf4j.Slf4j;
import org.repin.dto.NewDishDto;
import org.repin.dto.NewUserDto;
import org.repin.model.Dish;
import org.repin.model.MealIntake;
import org.repin.model.User;
import org.repin.repository.DishRepository;
import org.repin.repository.MealIntakeRepository;
import org.repin.repository.UserRepository;
import org.repin.service.CaloriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class MainController {
    private final UserRepository userRepository;
    private final CaloriesService caloriesService;
    private final DishRepository dishRepository;
    private final MealIntakeRepository mealIntakeRepository;
    @Autowired
    MainController(UserRepository userRepository,
                   CaloriesService caloriesService,
                   DishRepository dishRepository,
                   MealIntakeRepository mealIntakeRepository){
        this.userRepository = userRepository;
        this.caloriesService = caloriesService;
        this.dishRepository = dishRepository;
        this.mealIntakeRepository = mealIntakeRepository;
    }

    @PostMapping("/add_user")
    ResponseEntity<Object> addUser(@RequestBody NewUserDto dto){
        log.info("/add_user: {}", dto);

        User user = new User(dto.getName(),
                dto.getEmail(),
                dto.getAge(),
                dto.getWeight(),
                dto.getHeight(),
                dto.getGoal(),
                caloriesService.countDailyCalories(dto));

        return ResponseEntity.ok().body(userRepository.save(user).getDailyCalories()); //возвращаем лимит калорий
    }

    @PostMapping("/add_dish")
    ResponseEntity<Object> addDish(@RequestBody NewDishDto dto) {
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

    @PostMapping("/add_meal_intake")
    ResponseEntity<Object> addMealIntake(@RequestBody MealIntake mealIntake){
        log.info("/add_meal_intake: {}", mealIntake);

        if(!userRepository.existsById(mealIntake.getUserId())){
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }

        return ResponseEntity.ok().body(mealIntakeRepository.save(mealIntake));
    }
}
