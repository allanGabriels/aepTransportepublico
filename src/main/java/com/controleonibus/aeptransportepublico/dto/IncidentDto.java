package com.controleonibus.aeptransportepublico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IncidentDto(

        @NotNull(message = "O ID da viagem (tripId) não pode ser nulo") Long scheduleId,

        @NotNull(message = "O ID do fiscal (fiscalId) não pode ser nulo") Long fiscalId,

        @NotNull(message = "O ID do tipo de incidente (incidentType) não pode ser nulo") String incidentType,

        @NotNull(message = "O ID do tipo de incidente (incidentLevel) não pode ser nulo") String incidentLevel,

        @NotBlank(message = "A descrição não pode estar em branco") String description) {
}
