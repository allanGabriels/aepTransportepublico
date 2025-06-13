package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // para evitar carregamentos desnecessários
    @JoinColumn(name = "id_line")
    private Long id_line;

    private LocalTime departureTime;

}
