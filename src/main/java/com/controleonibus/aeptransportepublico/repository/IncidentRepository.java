package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    /*
     * List<Incident> findByFiscalId(Long fiscalId);
     * 
     * List<Incident> findByIncidentTypeName(String typeName);
     */
}
