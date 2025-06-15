package com.controleonibus.aeptransportepublico.dto;

public record IncidentTypeDto(String incidentType, String level) {

    public IncidentTypeDto {
        if (incidentType == null || incidentType.isBlank()) {
            throw new IllegalArgumentException("Tipo de incidente não pode ser nulo ou vazio");
        }
        if (level == null || level.isBlank()) {
            throw new IllegalArgumentException("Nível de incidente não pode ser nulo ou vazio");
        }
    }

}
