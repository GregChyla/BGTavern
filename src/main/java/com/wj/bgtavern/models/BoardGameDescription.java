package com.wj.bgtavern.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="board_game_descriptions")
public class BoardGameDescription {
    @Id
    private long boardGameId;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    // Gdyby pole id z opisu nie nazywało się boardGameId tylko np Id, to trzeba było by dołożyć
    // adnotację @JoinColumn(name = "Id")
    private BoardGame boardGame;
}
