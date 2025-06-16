package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate incidentDate;

    @ManyToOne
    @JoinColumn(name = "id_trip", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "id_fiscal", nullable = false)
    private User fiscal;

    @ManyToOne
    @JoinColumn(name = "id_incidentType", nullable = false)
    private IncidentType incidentType;

    public Incident() {
        // Construtor padrão do Jpa
    }

    public Incident(String description, Trip trip, User fiscal, IncidentType incidentType) {
        this.description = description;
        this.trip = trip;
        this.fiscal = fiscal;
        this.incidentType = incidentType;
        this.incidentDate = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDate incidentDate) {
        this.incidentDate = incidentDate;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public User getFiscal() {
        return fiscal;
    }

    public void setFiscal(User fiscal) {
        this.fiscal = fiscal;
    }

    public IncidentType getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public Long getId() {
        return id;
    }
}
