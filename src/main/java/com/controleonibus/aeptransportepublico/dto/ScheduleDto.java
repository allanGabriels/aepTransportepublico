package com.controleonibus.aeptransportepublico.dto;

import java.time.LocalTime;

public record ScheduleDto(Long lineId, LocalTime departureTime, Long driverId, Long busId) {

    public ScheduleDto {
        if (lineId == null || departureTime == null) {
            throw new IllegalArgumentException("Line_id ou horário não podem ser nulos");
        }
    }

}
