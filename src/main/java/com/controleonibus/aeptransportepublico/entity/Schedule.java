package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "line", nullable = false)
    private Line line;

    private LocalTime departureTime;

    public Schedule() {

    }

    public Schedule(Line line, LocalTime departureTime) {
        this.line = line;
        this.departureTime = departureTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
