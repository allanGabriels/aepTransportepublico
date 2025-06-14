package com.controleonibus.aeptransportepublico.dto;

public record LineDto(String number, String name) {

    public LineDto {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Número da linha não pode ser vazio");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da linha não pode ser vazio");
        }
    }

}
