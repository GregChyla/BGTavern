package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class BoardGameDescriptionRequestDto {
    private long id;
    @NotBlank
    private String description;
}
