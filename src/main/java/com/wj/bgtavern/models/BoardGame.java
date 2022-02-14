package com.wj.bgtavern.models;

import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

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

    @OneToOne(orphanRemoval = true, mappedBy = "boardGame")
    private BoardGameDescription description;
//
//    @OneToOne(mappedBy = "boardGame", cascade = CascadeType.ALL,
//              fetch = FetchType.LAZY, optional = false)
//    @LazyToOne(LazyToOneOption.NO_PROXY)
//    private BoardGameDescription description;
//
//    @OneToMany(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "boardGameId", updatable = false, insertable = false)
//    private List<BoardGameClassification> classifications;
//
//    @OneToMany
//    @JoinColumn(name = "coreGameId", updatable = false, insertable = false)
//    private List<BoardGameExpansion> expansions;   // gry które są rozszerzeniami podstawki
//
//    @OneToMany
//    @JoinColumn(name = "expansionGameId", updatable = false, insertable = false)
//    private List<BoardGameExpansion> cores;    // gry które rozszerza ten dodatek
//
//    @OneToMany
//    @JoinColumn(name = "boardGameId", updatable = false, insertable = false)
//    private List<BoardGameRelease> releases;
//
//    @OneToMany
//    @JoinColumn(name = "boardGameId", updatable = false, insertable = false)
//    private List<BoardGameTeamMember> teamMembers;

}
