package org.repin.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DailyReportDto {
    LocalDate date;
    Integer totalCalories;
    List<MealIntakeDto> mealIntakes;
}
