package org.repin.dto;

import lombok.Data;
import org.repin.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Data
public class MealIntakeDto {
    private Long id;
    private Long userId;
    private LocalDate date;
    private List<Dish> dishes;
}
