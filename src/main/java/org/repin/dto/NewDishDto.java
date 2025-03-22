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

}
