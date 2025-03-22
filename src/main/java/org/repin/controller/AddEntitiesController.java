package org.repin.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.repin.dto.NewDishDto;
import org.repin.dto.NewMealIntakeDto;
import org.repin.dto.NewUserDto;
import org.repin.model.Dish;
import org.repin.model.MealIntake;
import org.repin.model.MealIntakeDish;
import org.repin.model.User;
import org.repin.repository.DishRepository;
import org.repin.repository.MealIntakeDishRepository;
import org.repin.repository.MealIntakeRepository;
import org.repin.repository.UserRepository;
import org.repin.service.CaloriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/add")
@Slf4j
public class AddEntitiesController {
    private final UserRepository userRepository;
    private final CaloriesService caloriesService;
    private final DishRepository dishRepository;
    private final MealIntakeRepository mealIntakeRepository;
    private final MealIntakeDishRepository mealIntakeDishRepository;
    @Autowired
    AddEntitiesController(UserRepository userRepository,
                   CaloriesService caloriesService,
                   DishRepository dishRepository,
                   MealIntakeRepository mealIntakeRepository,
                   MealIntakeDishRepository mealIntakeDishRepository){
        this.userRepository = userRepository;
        this.caloriesService = caloriesService;
        this.dishRepository = dishRepository;
        this.mealIntakeRepository = mealIntakeRepository;
        this.mealIntakeDishRepository = mealIntakeDishRepository;
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
                caloriesService.countDailyCalories(dto));

        return ResponseEntity.ok().body(userRepository.save(user).getDailyCalories()); //возвращаем лимит калорий
    }

    @PostMapping("/dish")
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

    @PostMapping("/meal_intake")
    ResponseEntity<Object> addMealIntake(@RequestBody NewMealIntakeDto dto) {
        log.info("/add_meal_intake: {}", dto);

        if (!userRepository.existsById(dto.getUserId())) {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }

        final MealIntake mealIntake = mealIntakeRepository.save(new MealIntake(dto.getUserId(), dto.getDate()));

        // Загружаем блюда по их ID
        List<Dish> dishes = dishRepository.findAllById(dto.getDishes());

        // Создаем связи между MealIntake и Dish
        List<MealIntakeDish> intakeDishes = dishes.stream()
                .map(dish -> new MealIntakeDish(mealIntake, dish))
                .toList();

        // Сохраняем их
        mealIntakeDishRepository.saveAll(intakeDishes);

        return ResponseEntity.ok().body(mealIntake);
    }

}
