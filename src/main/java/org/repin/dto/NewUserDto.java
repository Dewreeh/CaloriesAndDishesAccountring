package org.repin.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.repin.enums.Goals;

import java.math.BigDecimal;

import java.math.BigDecimal;

@Data
public class NewUserDto {
    @NotBlank(message = "Имя обязательно")
    private String name;

    @NotBlank(message = "Email обязателен")
    @Email
    private String email;

    @NotNull(message = "Возраст обязателен")
    @Min(value = 3, message = "Возраст должен быть не менее 3 лет")
    @Max(value = 100, message = "Возраст должен быть меньше 100 лет")
    private Integer age;

    @NotNull(message = "Вес обязателен")
    @DecimalMin(value = "10.0", message = "Вес должен быть больше 10 кг")
    @DecimalMax(value = "300.0", message = "Вес должен быть меньше 300 кг")
    private BigDecimal weight;

    @NotNull(message = "Рост обязателен")
    @DecimalMin(value = "50.0", message = "Рост должен быть не менее 50 см")
    @DecimalMax(value = "250.0", message = "Рост должен быть меньше 250 см")
    private BigDecimal height;

    @NotNull(message = "Цель обязательна")
    private Goals goal;
}
