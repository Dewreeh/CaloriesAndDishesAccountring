package org.repin.repository;

import org.repin.model.MealIntake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealIntakeRepository extends JpaRepository<MealIntake, UUID> {
}
