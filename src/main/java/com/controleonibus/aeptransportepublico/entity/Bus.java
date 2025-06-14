package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private int number;

    @Getter
    @Setter
    private int seats;

    @Getter
    @Setter
    private Boolean electric;

    public Bus() {

    }

    public Bus(int number, int seats, Boolean electric) {
        this.number = number;
        this.seats = seats;
        this.electric = electric;
    }

}
