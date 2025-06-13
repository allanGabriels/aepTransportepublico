package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheduleRepository extends JpaRepository<Schedule, Long> {
}
