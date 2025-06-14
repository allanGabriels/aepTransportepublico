package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    private String number;

    @Getter
    @Setter
    private String name;

    public Line() {
    }

    public Line(String number, String name) {
        this.number = number;
        this.name = name;
    }

}
