package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomeCompleto;
    private String cpf;
    private String idade;

    @Enumerated
    private Enum funcao;
}
