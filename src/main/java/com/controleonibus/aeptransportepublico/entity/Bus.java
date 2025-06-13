package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private int lugares;
    private Boolean eletrico;
}
