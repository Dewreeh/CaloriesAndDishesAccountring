package org.repin.dto.report;

import lombok.Data;
import org.repin.model.Dish;
import org.repin.model.MealIntake;
import org.repin.model.MealIntakeDish;

import java.time.LocalDate;
import java.util.List;

@Data
public class DailyReportDto {
    LocalDate date;
    Integer totalCalories;
    List<MealIntakeReportDto> mealIntakes;
}
