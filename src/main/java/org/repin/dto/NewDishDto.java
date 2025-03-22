package org.repin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;
@Data
public class NewDishDto {

    private Long id;

    @NotBlank(message = "Название блюда не может быть пустым")
    private String name;

    @NotNull(message = "Не указаны калории")
    @Positive()
    private Integer calories;

    @NotNull(message = "Не указаны белки")
    @Positive()
    private BigDecimal proteins;

    @NotNull(message = "Не указаны калории")
    @Positive()
    private BigDecimal fats;

    @NotNull(message = "Не указаны калории")
    @Positive()
    private BigDecimal carbohydrates;
}

