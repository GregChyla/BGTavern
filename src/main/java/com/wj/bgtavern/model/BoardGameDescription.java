package com.wj.bgtavern.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="board_game_descriptions")
public class BoardGameDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardGameId;
    @Column(columnDefinition = "TEXT")
    private String description;
}
