package com.controleonibus.aeptransportepublico.dto;

public record BusDto(int number, int seats, Boolean electric) {

    public BusDto {
        if (number <= 0) {
            throw new IllegalArgumentException("Número do ônibus deve ser maior que zero");
        }
        if (seats <= 0) {
            throw new IllegalArgumentException("Número de assentos deve ser maior que zero");
        }
    }
}
