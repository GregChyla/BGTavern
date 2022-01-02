package com.wj.bgtavern.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="board_game_classifications")
public class BoardGameClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long boardGameId;
//    private long classificationId;
    private String description;

    @ManyToOne  //TODO: Jaki typ, żeby po usunięciu Classification został usunięty też BoardGameClassification
    @JoinColumn(name="classificationId")
    private Classification classification;
}
