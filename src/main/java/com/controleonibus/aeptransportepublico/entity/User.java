package com.controleonibus.aeptransportepublico.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    private String cpf;
    private String age;

    @Enumerated
    private Enum role;
}
