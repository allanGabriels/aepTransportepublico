package com.controleonibus.aeptransportepublico.dto;

import jakarta.validation.constraints.NotNull;

public record TripDto(
                @NotNull(message = "O ID do motorista não pode ser nulo") Long driverID,

                @NotNull(message = "O ID do ônibus não pode ser nulo") Long busID,

                @NotNull(message = "O ID do horário não pode ser nulo") Long scheduleID) {
}
