package com.controleonibus.aeptransportepublico.entity;

import com.controleonibus.aeptransportepublico.enums.UserRole;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    private String fullName;

    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private int age;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {
    }

    public User(String fullName, String cpf, int age, String role) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.age = age;
        this.role = UserRole.valueOf(role.toUpperCase());
    }

    public UserRole getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = UserRole.valueOf(role.toUpperCase());
    }

}
