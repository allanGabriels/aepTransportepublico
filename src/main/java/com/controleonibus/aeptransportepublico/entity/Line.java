package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numero;
    private String nome;

}
