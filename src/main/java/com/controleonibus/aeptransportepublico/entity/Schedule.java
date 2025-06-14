package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // para evitar carregamentos desnecessários
    @JoinColumn(name = "line_id", nullable = false)
    private Long line_id;

    private LocalTime departureTime;

}
