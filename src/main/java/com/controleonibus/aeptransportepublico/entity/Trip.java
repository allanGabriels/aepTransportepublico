package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tripDate;
    private String scheduledDepertureTime;
    private String actualDepartureTime;

}
