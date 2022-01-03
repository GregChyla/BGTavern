package com.wj.bgtavern.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="board_game_releases")
public class BoardGameRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    private long publisherId;
//    private long boardGameId;
//    private long releaseLanguageId;
    private LocalDate releaseDate;

    @OneToOne
    @JoinColumn(name = "releaseLanguageId", referencedColumnName = "id")
    private LanguageCode languageCode;

    @ManyToOne
    @JoinColumn(name = "boardGameId", referencedColumnName = "id")
    private BoardGame boardGame;

    @ManyToOne
    @JoinColumn(name = "publisherId", referencedColumnName = "id")
    private Publisher publisher;

}
