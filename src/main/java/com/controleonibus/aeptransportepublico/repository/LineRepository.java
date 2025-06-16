package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.Line;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
    Optional<Line> findByName(String name);

    Optional<Line> findByNumber(String number);

}
