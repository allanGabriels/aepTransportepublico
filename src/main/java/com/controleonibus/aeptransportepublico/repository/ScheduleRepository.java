package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
