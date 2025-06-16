package com.controleonibus.aeptransportepublico.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import com.controleonibus.aeptransportepublico.dto.IncidentDto;
import com.controleonibus.aeptransportepublico.entity.Incident;
import com.controleonibus.aeptransportepublico.entity.IncidentType;
import com.controleonibus.aeptransportepublico.entity.Trip;
import com.controleonibus.aeptransportepublico.entity.User;
import com.controleonibus.aeptransportepublico.enums.IncidentLevel;
import com.controleonibus.aeptransportepublico.enums.IncidentTypes;
import com.controleonibus.aeptransportepublico.repository.IncidentRepository;
import com.controleonibus.aeptransportepublico.repository.IncidentTypeRepository;
import com.controleonibus.aeptransportepublico.repository.TripRepository;
import com.controleonibus.aeptransportepublico.repository.UserRepository;

@RestController
@RequestMapping("/incidents")
@CrossOrigin(origins = "http://localhost:8000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.OPTIONS })
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
        public Incident save(@Valid @RequestBody IncidentDto incidentDto) {

                Trip trip = tripRepository.findByScheduleId(incidentDto.scheduleId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Trip não encontrada para o scheduleId: " + incidentDto.scheduleId()));

                User fiscal = userRepository.findById(incidentDto.fiscalId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Fiscal não encontrado com o ID: " + incidentDto.fiscalId()));

                IncidentTypes tipoEnum;
                IncidentLevel nivelEnum;
                try {
                        tipoEnum = IncidentTypes.valueOf(incidentDto.incidentType().toUpperCase());
                        nivelEnum = IncidentLevel.valueOf(incidentDto.incidentLevel().toUpperCase());
                } catch (IllegalArgumentException e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "Valor de incidentType ou incidentLevel inválido");
                }
                IncidentType incidentType = incidentTypeRepository
                                .findByIncidentTypeAndLevel(tipoEnum, nivelEnum)
                                .orElseGet(() -> {
                                        IncidentType newType = new IncidentType();
                                        newType.setIncidentType(incidentDto.incidentType());
                                        newType.setLevel(incidentDto.incidentLevel());
                                        return incidentTypeRepository.save(newType);
                                });

                Incident newIncident = new Incident(
                                incidentDto.description(),
                                trip,
                                fiscal,
                                incidentType);
                return incidentRepository.save(newIncident);
        }

        @PutMapping("/{id}")
        public Incident update(@PathVariable Long id, @Valid @RequestBody IncidentDto incidentDto) {
                Incident existingIncident = incidentRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Incidente não encontrado com o ID: " + id));

                Trip trip = tripRepository.findByScheduleId(incidentDto.scheduleId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Trip não encontrada para o scheduleId: " + incidentDto.scheduleId()));

                User fiscal = userRepository.findById(incidentDto.fiscalId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Fiscal não encontrado com o ID: " + incidentDto.fiscalId()));

                IncidentTypes tipoEnum;
                IncidentLevel nivelEnum;

                try {
                        tipoEnum = IncidentTypes.valueOf(incidentDto.incidentType().toUpperCase());
                        nivelEnum = IncidentLevel.valueOf(incidentDto.incidentLevel().toUpperCase());
                } catch (IllegalArgumentException e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "Valor de incidentType ou incidentLevel inválido");
                }

                IncidentType incidentType = incidentTypeRepository
                                .findByIncidentTypeAndLevel(tipoEnum, nivelEnum)
                                .orElseGet(() -> {
                                        IncidentType newType = new IncidentType();
                                        newType.setIncidentType(incidentDto.incidentType());
                                        newType.setLevel(incidentDto.incidentLevel());
                                        return incidentTypeRepository.save(newType);
                                });

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
