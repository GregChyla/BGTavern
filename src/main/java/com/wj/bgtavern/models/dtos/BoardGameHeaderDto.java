package com.wj.bgtavern.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BoardGameHeaderDto {
    private long id;
    private String name;
}
