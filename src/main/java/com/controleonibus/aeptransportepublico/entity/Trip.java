package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.controleonibus.aeptransportepublico.enums.TripStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tripDate;

    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private LocalTime scheduledDepartureTime;
    private LocalTime actualDepartureTime;

    public Trip() {
    }

    public Trip(User driver, Bus bus, Schedule schedule, LocalTime scheduledDepartureTime) {
        this.tripDate = LocalDate.now();
        this.tripStatus = TripStatus.SCHEDULED;
        this.driver = driver;
        this.bus = bus;
        this.schedule = schedule;
        this.scheduledDepartureTime = scheduledDepartureTime;
        // this.actualDepartureTime =
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public LocalTime getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(LocalTime scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public LocalTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    public void setActualDepartureTime(LocalTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    public Long getId() {
        return id;
    }
}
