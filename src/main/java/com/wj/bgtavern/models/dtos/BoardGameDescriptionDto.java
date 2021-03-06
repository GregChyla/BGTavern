package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class BoardGameDescriptionDto {

    @NotNull @Min(1)
    private Long boardGameId;
    @NotBlank
    private String description;
}
