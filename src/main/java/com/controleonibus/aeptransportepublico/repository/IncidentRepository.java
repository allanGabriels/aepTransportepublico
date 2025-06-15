package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    /*
     * List<Incident> findByFiscalId(Long fiscalId);
     * 
     * List<Incident> findByIncidentTypeName(String typeName);
     */
}
