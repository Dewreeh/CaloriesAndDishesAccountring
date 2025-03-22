package org.repin.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.repin.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MealIntakeReportDto {
    private UUID id;
    private LocalDate date;
    private List<Dish> dishes;

    public int getTotalCalories() {
        return dishes.stream().mapToInt(Dish::getCalories).sum();
    }
}
