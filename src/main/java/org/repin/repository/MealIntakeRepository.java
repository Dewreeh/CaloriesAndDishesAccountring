package org.repin.repository;

import org.repin.model.MealIntake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MealIntakeRepository extends JpaRepository<MealIntake, UUID> {
    List<MealIntake> findAllByUserIdAndDate(UUID userId, LocalDate date);
}
