package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate incidentDate;

    @Enumerated(EnumType.STRING)
    private Enum status;

    @ManyToOne(fetch = FetchType.LAZY) // faz com que os dados sejam carregando quando necessário
    @JoinColumn(name = "id_trip", nullable = false)
    private Long trip_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fiscal", nullable = false)
    private Long fiscal_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidentType", nullable = false)
    private Long incidentType_Id;

    public Incident() {
        // Construtor padrão do Jpa
    }

    public Incident(String description, Enum status, Long trip_id, Long fiscal_id, Long incidentType_id) {
        this.description = description;
        this.status = status;
        this.trip_id = trip_id;
        this.fiscal_id = fiscal_id;
        this.incidentType_Id = incidentType_id;
        this.incidentDate = LocalDate.now();
    }

}
