package org.repin.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "meal_intakes")
@Data
public class MealIntake {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;

    private LocalDate date;

    public MealIntake(UUID userId, LocalDate date) {
        this.userId = userId;
        this.date = date;
    }
    public MealIntake() {
    }
}
