package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardGameResponseDto {
    private long id;
    private String name;
    private int playingTime;
    private int age;
    private int minPlayersNumber;
    private int maxPlayersNumber;
    private double complexity;
    private int languageDependence;
    private String description;
}
