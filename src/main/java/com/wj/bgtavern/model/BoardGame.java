package com.wj.bgtavern.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="board_games")
public class BoardGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int playingTime;
    private int age;
    private int minPlayersNumber;
    private int maxPlayersNumber;
    @Column(columnDefinition="DECIMAL", precision=3, scale=2)
    private double complexity;
    private int languageDependence;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id", referencedColumnName = "boardGameId")
    @NotFound(action = NotFoundAction.IGNORE)
    private BoardGameDescription boardGameDescription;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "boardGameId")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<BoardGameClassification> boardGameClassification;
}
