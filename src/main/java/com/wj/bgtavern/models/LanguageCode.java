package com.wj.bgtavern.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="language_codes")
public class LanguageCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String isoLanguageName;
    @Column(name="iso_6391")
    private String iso6391;
    @Column(name="iso_6392t")
    private String iso6392t;
    @Column(name="iso_6392b")
    private String iso6392b;
}
