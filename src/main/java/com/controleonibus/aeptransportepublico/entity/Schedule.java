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

    @ManyToOne
    @JoinColumn(name = "driver", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "bus", nullable = false)
    private Bus bus;

    public Schedule() {

    }

    public Schedule(Line line, LocalTime departureTime, User driver, Bus bus) {
        this.line = line;
        this.departureTime = departureTime;
        this.driver = driver;
        this.bus = bus;
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

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Long getId() {
        return id;
    }
}
