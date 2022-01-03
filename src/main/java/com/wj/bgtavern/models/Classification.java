package com.wj.bgtavern.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String alterNames;
    private String description;
//    private long classificationTypeId;

    @ManyToOne  //TODO: Jaki typ, żeby po usunięciu Classification został usunięty też BoardGameClassification
    @JoinColumn(name="classificationTypeId")
    private ClassificationType classificationType;

}
