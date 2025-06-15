package com.controleonibus.aeptransportepublico.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        public Incident find(@PathVariable Long id) {
                return incidentRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Onibus não encontrado"));

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
        public Incident update(@PathVariable Long id, @RequestBody IncidentDto incidentDto) {
                Incident existingIncident = incidentRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Incidente não encontrado"));

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
        public void delete(@PathVariable Long id) {
                Incident existingIncident = incidentRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Incidente não encontrado"));
                incidentRepository.delete(existingIncident);
        }

}
