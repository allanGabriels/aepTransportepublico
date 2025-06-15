package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.controleonibus.aeptransportepublico.enums.TripStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
