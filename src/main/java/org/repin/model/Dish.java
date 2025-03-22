package org.repin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "dishes")
@Data
public class Dish {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Integer calories;

    private BigDecimal proteins;

    private BigDecimal fats;

    private BigDecimal carbohydrates;

    public Dish(String name, Integer calories, BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
}
