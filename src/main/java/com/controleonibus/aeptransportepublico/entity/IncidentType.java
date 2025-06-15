package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.controleonibus.aeptransportepublico.enums.IncidentLevel;
import com.controleonibus.aeptransportepublico.enums.IncidentTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class IncidentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private IncidentTypes incidentType;

    @Enumerated
    private IncidentLevel level;

    public IncidentTypes getIncidentType() {
        return this.incidentType;
    }

    public void setIncidentType(IncidentTypes incidentType) {
        this.incidentType = incidentType;
    }

    public IncidentLevel getLevel() {
        return this.level;
    }

    public void setLevel(IncidentLevel level) {
        this.level = level;
    }

    public IncidentType() {
    }

}
