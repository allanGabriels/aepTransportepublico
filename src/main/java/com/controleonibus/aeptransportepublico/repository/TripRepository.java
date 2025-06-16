package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Optional<Trip> findByScheduleId(Long scheduleId);
}
