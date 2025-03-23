package org.repin.dto.report;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class NutritionHistoryDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private IsDailyLimitKeptDto isDailyLimitKept;
    private List<MealIntakeReportDto> mealIntakes;
}
