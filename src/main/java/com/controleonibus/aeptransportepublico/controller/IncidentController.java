package com.controleonibus.aeptransportepublico.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.controleonibus.aeptransportepublico.dto.IncidentDto;
import com.controleonibus.aeptransportepublico.entity.Incident;
import com.controleonibus.aeptransportepublico.entity.IncidentType;
import com.controleonibus.aeptransportepublico.entity.Trip;
import com.controleonibus.aeptransportepublico.entity.User;
import com.controleonibus.aeptransportepublico.repository.IncidentRepository;
import com.controleonibus.aeptransportepublico.repository.IncidentTypeRepository;
import com.controleonibus.aeptransportepublico.repository.TripRepository;
import com.controleonibus.aeptransportepublico.repository.UserRepository;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    public IncidentRepository incidentRepository;
    public TripRepository tripRepository;
    public UserRepository userRepository;
    public IncidentTypeRepository incidentTypeRepository;

    public IncidentController(IncidentRepository incidentRepository,
            TripRepository tripRepository,
            UserRepository userRepository,
            IncidentTypeRepository incidentTypeRepository) {
        this.incidentRepository = incidentRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.incidentTypeRepository = incidentTypeRepository;
    }

    @GetMapping
    public List<Incident> list() {
        return incidentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Incident find(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Onibus não encontrado"));

    }

    @GetMapping("/bus/{busId}")
    public List<Incident> findByBusId(Long busId) {
        List<Incident> incidents = incidentRepository.findByBusId(busId);
        if (incidents.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum incidente encontrado para o ônibus");
        }
        return incidents;
    }

    @GetMapping("/fiscal/{fiscalId}")
    public List<Incident> findByFiscalId(Long fiscalId) {
        List<Incident> incidents = incidentRepository.findByFiscalId(fiscalId);
        if (incidents.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum incidente encontrado para o fiscal");
        }
        return incidents;
    }

    @GetMapping("/type/{level}")
    public List<Incident> findByIncidentType(String level) {
        List<Incident> incidents = incidentRepository.findByIncidentType(level);
        if (incidents.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Nenhum incidente encontrado para o tipo especificado");
        }
        return incidents;
    }

    @PostMapping
    public Incident save(@RequestBody IncidentDto incidentDto) {
        Trip trip = tripRepository.findById(incidentDto.tripId())
                .orElseThrow(() -> new RuntimeException("Trip não encontrada"));

        User fiscal = userRepository.findById(incidentDto.fiscalId())
                .orElseThrow(() -> new RuntimeException("Fiscal não encontrado"));

        IncidentType incidentType = incidentTypeRepository.findById(incidentDto.incidentType())
                .orElseThrow(() -> new RuntimeException("Incident Type não encontrado"));

        Incident newIncident = new Incident(
                incidentDto.description(),
                trip,
                fiscal,
                incidentType);

        return incidentRepository.save(newIncident);
    }

    @PutMapping("/{id}")
    public Incident update(@RequestBody IncidentDto incidentDto, Long id) {
        Incident existingIncident = incidentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incidente não encontrado"));

        Trip trip = tripRepository.findById(incidentDto.tripId())
                .orElseThrow(() -> new RuntimeException("Trip não encontrada"));

        User fiscal = userRepository.findById(incidentDto.fiscalId())
                .orElseThrow(() -> new RuntimeException("Fiscal não encontrado"));

        IncidentType incidentType = incidentTypeRepository.findById(incidentDto.incidentType())
                .orElseThrow(() -> new RuntimeException("Incident Type não encontrado"));

        existingIncident.setDescription(incidentDto.description());
        existingIncident.setTrip(trip);
        existingIncident.setFiscal(fiscal);
        existingIncident.setIncidentType(incidentType);

        return incidentRepository.save(existingIncident);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        Incident existingIncident = incidentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incidente não encontrado"));
        incidentRepository.delete(existingIncident);
    }

}
