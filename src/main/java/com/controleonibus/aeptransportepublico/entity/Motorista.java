package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Motorista extends Pessoa{
   @OneToOne
   @JoinColumn(name = "bus_id")
   private Bus actualBus;
}
