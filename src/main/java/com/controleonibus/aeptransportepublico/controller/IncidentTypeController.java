package com.controleonibus.aeptransportepublico.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.controleonibus.aeptransportepublico.repository.IncidentTypeRepository;
import com.controleonibus.aeptransportepublico.dto.IncidentTypeDto;
import com.controleonibus.aeptransportepublico.entity.IncidentType;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/incidentTypes")
public class IncidentTypeController {

    private final IncidentTypeRepository incidentTypeRepository;

    public IncidentTypeController(IncidentTypeRepository incidentTypeRepository) {
        this.incidentTypeRepository = incidentTypeRepository;
    }

    @GetMapping
    public List<IncidentType> list() {
        return incidentTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public IncidentType find(@PathVariable Long id) {
        return incidentTypeRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de incidente não encontrado"));
    }

    @PostMapping
    public IncidentType save(@RequestBody IncidentTypeDto incidentTypeDto) {
        IncidentType newincidentType = new IncidentType(
                incidentTypeDto.incidentType(),
                incidentTypeDto.level());

        return incidentTypeRepository.save(newincidentType);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!incidentTypeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de incidente não encontrado");
        }
        incidentTypeRepository.deleteById(id);
    }

}
