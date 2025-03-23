package org.repin.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.repin.dto.NewMealIntakeDto;
import org.repin.model.Dish;
import org.repin.model.MealIntake;
import org.repin.model.MealIntakeDish;
import org.repin.repository.DishRepository;
import org.repin.repository.MealIntakeDishRepository;
import org.repin.repository.MealIntakeRepository;
import org.repin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealIntakeService {
    private final UserRepository userRepository;
    private final MealIntakeRepository mealIntakeRepository;
    private final DishRepository dishRepository;
    private final MealIntakeDishRepository mealIntakeDishRepository;

    @Autowired
    public MealIntakeService(UserRepository userRepository,
                             MealIntakeRepository mealIntakeRepository,
                             DishRepository dishRepository,
                             MealIntakeDishRepository mealIntakeDishRepository) {
        this.userRepository = userRepository;
        this.mealIntakeRepository = mealIntakeRepository;
        this.dishRepository = dishRepository;
        this.mealIntakeDishRepository = mealIntakeDishRepository;
    }

    @Transactional
    public MealIntake addMealIntake(NewMealIntakeDto dto) {
        if (!userRepository.existsById(dto.getUserId())) {
            throw new EntityNotFoundException("Пользователь не найден");
        }

        MealIntake mealIntake = mealIntakeRepository.save(new MealIntake(dto.getUserId(), dto.getDate()));

        List<Dish> dishes = dishRepository.findAllById(dto.getDishes());

        if (dishes.size() != dto.getDishes().size()) {
            throw new EntityNotFoundException("Одно или несколько блюд не найдены");
        }

        List<MealIntakeDish> intakeDishes = dishes.stream()
                .map(dish -> new MealIntakeDish(mealIntake, dish))
                .toList();

        mealIntakeDishRepository.saveAll(intakeDishes);

        return mealIntake;
    }
}
