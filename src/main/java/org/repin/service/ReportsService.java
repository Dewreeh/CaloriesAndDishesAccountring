package org.repin.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.repin.dto.report.DailyReportDto;
import org.repin.dto.report.IsDailyLimitKeptDto;
import org.repin.dto.report.MealIntakeReportDto;
import org.repin.dto.report.NutritionHistoryDto;
import org.repin.model.Dish;
import org.repin.model.User;
import org.repin.model.MealIntake;
import org.repin.model.MealIntakeDish;
import org.repin.repository.DishRepository;
import org.repin.repository.MealIntakeDishRepository;
import org.repin.repository.MealIntakeRepository;
import org.repin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReportsService {

    private final UserRepository userRepository;
    private final CaloriesService caloriesService;

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
        this.mealIntakeRepository = mealIntakeRepository;
        this.mealIntakeDishRepository = mealIntakeDishRepository;
    }

    @Transactional
    public DailyReportDto getDailyReport(UUID userId, LocalDate date){
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Пользователь не найден");
        }

        DailyReportDto report = new DailyReportDto();

        //Все приёмы пищи пользователя за день
        List<MealIntake> dailyMeals = getMealsForDay(userId, date);
        //Список из всех приёмов пищи за день c блюдами
        List<MealIntakeReportDto> mealIntakeDtos = createMealIntakеListForDay(dailyMeals, date);


        //устанавливаем значения в итоговую дто
        report.setDate(date);
        report.setMealIntakes(mealIntakeDtos);
        report.setTotalCalories(caloriesService.calculateTotalCalories(mealIntakeDtos));

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

    public NutritionHistoryDto getNutritionHistory(UUID userId, LocalDate startDate, LocalDate endDate) {
        NutritionHistoryDto historyDto = new NutritionHistoryDto();
        historyDto.setStartDate(startDate);
        historyDto.setEndDate(endDate);

        Map<LocalDate, DailyReportDto> dateReportMap = new TreeMap<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            DailyReportDto dailyReport = getDailyReport(userId, currentDate);
            dateReportMap.put(currentDate, dailyReport);
            currentDate = currentDate.plusDays(1);
        }

        historyDto.setDateReportMap(dateReportMap);
        return historyDto;
    }


    private List<MealIntakeReportDto> createMealIntakеListForDay(List<MealIntake> dailyMeals, LocalDate date) {
        List<MealIntakeReportDto> mealIntakeDtos = new ArrayList<>();
        for (MealIntake meal : dailyMeals) {
            List<Dish> dishes = getDishesByMeal(meal);
            mealIntakeDtos.add(new MealIntakeReportDto(meal.getId(), date, dishes));
        }
        return mealIntakeDtos;
    }

    private List<MealIntake> getMealsForDay(UUID userId, LocalDate date){
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
