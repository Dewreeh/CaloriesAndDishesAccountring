package org.repin.dto;

import lombok.Data;
import org.repin.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class MealIntakeDto {
    private Long id;
    private UUID userId;
    private LocalDate date;
    private List<UUID> dishes;
}
