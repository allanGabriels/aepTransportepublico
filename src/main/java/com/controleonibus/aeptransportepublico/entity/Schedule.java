package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // para evitar carregamentos desnecessários
    @JoinColumn(name = "line", nullable = false)
    private Line line;

    private LocalTime departureTime;

    public Schedule() {

    }

    public Schedule(Line line, LocalTime departureTime) {
        this.line = line;
        this.departureTime = departureTime;
    }

}
