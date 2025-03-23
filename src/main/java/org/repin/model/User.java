package org.repin.model;

import jakarta.persistence.*;
import lombok.Data;
import org.repin.enums.Goals;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    UUID id;
    String name;
    String email;
    Integer age;
    BigDecimal weight;
    BigDecimal height;
    @Enumerated(EnumType.STRING)
    Goals goal;
    Integer dailyCalories;

    public User(String name, String email, Integer age, BigDecimal weight, BigDecimal height, Goals goal, Integer dailyLimitOfCalories) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.dailyCalories = dailyLimitOfCalories;
    }

    public User(){}
}
