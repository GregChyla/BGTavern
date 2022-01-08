package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Builder
public class BoardGameRequestDto {
    // TODO: Make possible to pass only few attributes e.g: when user want to change only name or only description
    // TODO: Make PATCH method instead of PUT method to edit only few attributes

    @NotBlank @Length(max = 255)
    private String name;
    @NotNull(message = "Field ") @Min(0)
    private int playingTime;
    @NotNull @Min(0)
    private int age;
    @NotNull @Min(1)
    private int minPlayersNumber;
    @NotNull @Min(1)
    private int maxPlayersNumber;
    @NotNull @DecimalMin("0.0") @DecimalMax("5.0")
    private double complexity;
    @NotNull @Min(0) @Max(5)
    private int languageDependence;
    @NotBlank
    private String description;
}
