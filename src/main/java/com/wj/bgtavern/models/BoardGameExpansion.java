package com.wj.bgtavern.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="board_game_expansions")
public class BoardGameExpansion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long expansionGameId;
    private long coreGameId;
    private String description;


}
