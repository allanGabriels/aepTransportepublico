package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;

@Entity
public class GradeDiaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
