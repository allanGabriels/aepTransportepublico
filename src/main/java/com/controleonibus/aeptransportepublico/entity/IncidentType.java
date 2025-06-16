package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.controleonibus.aeptransportepublico.enums.IncidentLevel;
import com.controleonibus.aeptransportepublico.enums.IncidentTypes;

import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class IncidentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private IncidentTypes incidentType;

    @Enumerated(EnumType.STRING)
    private IncidentLevel level;

    public IncidentType(String incidentType, String level) {
        this.incidentType = IncidentTypes.valueOf(incidentType.toUpperCase());
        this.level = IncidentLevel.valueOf(level.toUpperCase());
    }

    public IncidentTypes getIncidentType() {
        return this.incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType != null ? IncidentTypes.valueOf(incidentType.toUpperCase()) : null;
    }

    public IncidentLevel getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level != null ? IncidentLevel.valueOf(level.toUpperCase()) : null;
    }

    public IncidentType() {
    }

    public Long getId() {
        return this.id;
    }

}
