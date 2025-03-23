package org.repin.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import net.bytebuddy.asm.Advice;
import org.repin.dto.report.DailyReportDto;
import org.repin.dto.report.IsDailyLimitKeptDto;
import org.repin.dto.report.MealIntakeReportDto;
import org.repin.model.Dish;
import org.repin.model.User;
import org.repin.model.MealIntake;
import org.repin.model.MealIntakeDish;
import org.repin.repository.DishRepository;
import org.repin.repository.MealIntakeDishRepository;
import org.repin.repository.MealIntakeRepository;
import org.repin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReportsService {

    private final UserRepository userRepository;
    private final CaloriesService caloriesService;
    private final DishRepository dishRepository;
    private final MealIntakeRepository mealIntakeRepository;
    private final MealIntakeDishRepository mealIntakeDishRepository;

    @Autowired
    ReportsService(UserRepository userRepository,
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

    public DailyReportDto getDailyReport(UUID userId, LocalDate date){
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Пользователь не найден");
        }

        DailyReportDto report = new DailyReportDto();

        //Все приёмы пищи пользователя за день
        List<MealIntake> dailyMeals = getMealsForDay(userId, date);

        List<MealIntakeReportDto> mealIntakeDtos = new ArrayList<>();

        int totalCalories = 0;

        //проходим по всем приемам пищи, получаем блюда для каждого. Формируем Список всех приёмов за день  с блюдами в них
        for(MealIntake meal: dailyMeals){
            List<Dish> dishes = getDishesByMeal(meal);

            MealIntakeReportDto mealIntake = new MealIntakeReportDto(meal.getId(), date, dishes);

            mealIntakeDtos.add(mealIntake);

            totalCalories += mealIntake.getTotalCalories();
        }

        //устанавливаем значения в итоговую дто
        report.setDate(date);
        report.setMealIntakes(mealIntakeDtos);
        report.setTotalCalories(totalCalories);

        return report;
    }

    public IsDailyLimitKeptDto isDailyLimitKept(UUID userId, LocalDate date){
        IsDailyLimitKeptDto isDailyLimitKeptDto = new IsDailyLimitKeptDto();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        DailyReportDto dailyReport = getDailyReport(userId, date);

        Integer userCaloriesLimit = user.getDailyCalories(); //лимит калорий юзера

        Integer caloriesForDay =  dailyReport.getTotalCalories(); //набранные калории на конкретный день

        isDailyLimitKeptDto.setIsKept(caloriesForDay < userCaloriesLimit);
        isDailyLimitKeptDto.setDifferenceFromLimit(Math.abs(caloriesForDay - userCaloriesLimit)); //считаем отклонение от лимита

        return isDailyLimitKeptDto;
    }


    private List<MealIntake> getMealsForDay(UUID userId, LocalDate date){
        //Все приёмы пищи пользователя за день
        return mealIntakeRepository.findAllByUserIdAndDate(userId, date);
    }

    private List<Dish> getDishesByMeal(MealIntake mealIntake){
        List<MealIntakeDish> mealsAndDishesMap = mealIntakeDishRepository.findAllByMealIntakeId(mealIntake.getId());

        List<Dish> dishes = new ArrayList<>();

        return mealsAndDishesMap.stream()
                .map(MealIntakeDish::getDish)
                .toList();
    }
}
