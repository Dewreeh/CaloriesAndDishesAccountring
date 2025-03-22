package org.repin.dto;

import lombok.Data;
import org.repin.enums.Goals;

import java.math.BigDecimal;

@Data
public class NewUserDto {
    String name;
    String email;
    Integer age;
    BigDecimal weight;
    BigDecimal height;
    Goals goal;
}