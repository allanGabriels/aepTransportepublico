package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
}
