package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private int seats;

    private Boolean electric;

    public Bus() {

    }

    public Bus(int number, int seats, Boolean electric) {
        this.number = number;
        this.seats = seats;
        this.electric = electric;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setElectric(Boolean electric) {
        this.electric = electric;
    }

    public int getNumber() {
        return this.number;
    }

    public int getSeats() {
        return this.seats;
    }

    public Boolean getElectric() {
        return this.electric;
    }

    public Boolean isElectric() {
        return this.electric;
    }

}
