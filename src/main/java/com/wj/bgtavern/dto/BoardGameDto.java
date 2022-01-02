package com.wj.bgtavern.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class BoardGameDto {
    private long id;
    private String name;
    private int playingTime;
    private int age;
    private int minPlayersNumber;
    private int maxPlayersNumber;
    private double complexity;
    private int languageDependence;
}
