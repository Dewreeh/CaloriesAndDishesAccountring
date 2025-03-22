package org.repin.repository;

import org.repin.model.Dish;
import org.repin.model.MealIntake;
import org.repin.model.MealIntakeDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MealIntakeDishRepository extends JpaRepository<MealIntakeDish, UUID> {
    List<MealIntakeDish> findAllByMealIntakeId(UUID mealIntakeId);
}
