package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Builder
public class BoardGameDto {
    @Min(0)
    private long id;
    @Length(min = 1, max = 255)
    private String name;
    @Min(0)
    private int playingTime;
    @Min(0)
    private int age;
    @Min(1)
    private int minPlayersNumber;
    @Min(1)
    private int maxPlayersNumber;
    @DecimalMin("0.0") @DecimalMax("5.0") // TODO: Do a validation for decimal precision of double field
    private double complexity;
    @Min(0) @Max(5)
    private int languageDependence;
    private String description;
}
