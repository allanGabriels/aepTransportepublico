package com.controleonibus.aeptransportepublico.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private LocalDate dataOcorrencia;

    @Enumerated(EnumType.STRING)
    private Enum status;

    @ManyToOne(fetch = FetchType.LAZY) // faz com que os dados sejam carregando quando necessário
    @JoinColumn(name = "id_motorista", nullable = false)
    private Long id_motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_onibus", nullable = false)
    private Long id_onibus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false)
    private Long id_schedule;

    public Ocorrencia() {
        // Construtor padrão do Jpa
    }

    public Ocorrencia(String descricao, Enum status, Long id_motorista, Long id_onibus, Long id_schedule) {
        this.descricao = descricao;
        this.status = status;
        this.id_motorista = id_motorista;
        this.id_onibus = id_onibus;
        this.id_schedule = id_schedule;
        this.dataOcorrencia = LocalDate.now();
    }

}
