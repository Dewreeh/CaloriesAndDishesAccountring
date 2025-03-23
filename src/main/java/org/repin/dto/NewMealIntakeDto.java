package org.repin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class NewMealIntakeDto {
    private Long id;
    @NotNull
    private UUID userId;
    @NotNull
    private LocalDate date;
    @NotNull
    private List<UUID> dishes;
}
