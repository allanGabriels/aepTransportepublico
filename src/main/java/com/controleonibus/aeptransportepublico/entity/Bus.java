package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private int seats;
    private Boolean electric;
}
