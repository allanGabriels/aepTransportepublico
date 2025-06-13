package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataViagem;
    private String horaSaida;
    private String horaRealSaida;

}
