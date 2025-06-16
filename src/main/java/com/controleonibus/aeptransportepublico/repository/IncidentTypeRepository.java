package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.IncidentType;
import com.controleonibus.aeptransportepublico.enums.IncidentLevel;
import com.controleonibus.aeptransportepublico.enums.IncidentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IncidentTypeRepository extends JpaRepository<IncidentType, Long> {

    Optional<IncidentType> findByIncidentTypeAndLevel(IncidentTypes incidentType, IncidentLevel level);
}