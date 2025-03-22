package org.repin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "meal_intake_dishes")
@Data
public class MealIntakeDish {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "meal_intake_id")
    private MealIntake mealIntake;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    public MealIntakeDish(MealIntake mealIntake, Dish dish) {
        this.mealIntake = mealIntake;
        this.dish = dish;
    }

    public MealIntakeDish() {
    }
}
