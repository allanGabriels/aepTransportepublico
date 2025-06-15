package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalDate;
import com.controleonibus.aeptransportepublico.enums.IncidentTypes;
import com.controleonibus.aeptransportepublico.entity.User;
import com.controleonibus.aeptransportepublico.entity.IncidentType;
import com.controleonibus.aeptransportepublico.entity.Trip;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate incidentDate;

    @ManyToOne(fetch = FetchType.LAZY) // faz com que os dados sejam carregando quando necessário
    @JoinColumn(name = "id_trip", nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fiscal", nullable = false)
    private User fiscal;

    @ManyToOne(fetch = FetchType.LAZY)
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

}
