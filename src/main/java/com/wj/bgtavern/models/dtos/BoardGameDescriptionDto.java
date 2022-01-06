package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardGameDescriptionDto {

    private Long boardGameId;
    private String description;
}
