package org.repin.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class NewDishDto {
    private Long id;
    private String name;
    private Integer calories;
    private BigDecimal proteins;
    private BigDecimal fats;
    private BigDecimal carbohydrates;

    public NewDishDto(String name, Integer calories, BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
}
