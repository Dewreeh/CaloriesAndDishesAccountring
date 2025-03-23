package org.repin.service;

import org.repin.dto.report.MealIntakeReportDto;
import org.repin.enums.Goals;
import org.repin.dto.NewUserDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CaloriesService {

    public Integer countDailyCalories(NewUserDto dto){
        BigDecimal height = dto.getHeight();
        BigDecimal weight = dto.getWeight();
        BigDecimal age = BigDecimal.valueOf(dto.getAge());

        BigDecimal dailyCalories;

        //Т.к. в информации о пользователе не храним пол, то использую просто формулу для мужчин
        dailyCalories = BigDecimal.valueOf(88.362)
                .add(BigDecimal.valueOf(13.397).multiply(weight))
                .add(BigDecimal.valueOf(4.799).multiply(height))
                .subtract(BigDecimal.valueOf(5.677).multiply(age));


        switch(dto.getGoal()){
            case Goals.ПОХУДЕНИЕ -> dailyCalories = dailyCalories.multiply(BigDecimal.valueOf(0.8));
            case Goals.НАБОР_МАССЫ -> dailyCalories = dailyCalories.multiply(BigDecimal.valueOf(1.2));
            //а в случае поддержания ни на что не умножаем
        }

        return dailyCalories.intValue();
    }

    public int calculateTotalCalories(List<MealIntakeReportDto> mealIntakeDtos) {
        return mealIntakeDtos.stream()
                .mapToInt(MealIntakeReportDto::getTotalCalories)
                .sum();
    }
}
