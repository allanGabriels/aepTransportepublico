package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;

@Entity
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Enum tipo;

    @OneToOne
    @JoinColumn(name = "motoristaResponsavel")
    private Motorista motoristaResponsavel;



}
