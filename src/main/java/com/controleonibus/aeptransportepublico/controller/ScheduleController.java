package com.controleonibus.aeptransportepublico.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.controleonibus.aeptransportepublico.repository.LineRepository;
import com.controleonibus.aeptransportepublico.repository.ScheduleRepository;
import com.controleonibus.aeptransportepublico.repository.UserRepository;
import com.controleonibus.aeptransportepublico.repository.BusRepository;
import com.controleonibus.aeptransportepublico.entity.Schedule;
import com.controleonibus.aeptransportepublico.dto.ScheduleDto;
import com.controleonibus.aeptransportepublico.entity.Line;
import com.controleonibus.aeptransportepublico.entity.User;
import com.controleonibus.aeptransportepublico.entity.Bus;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

        public ScheduleRepository scheduleRepository;
        public LineRepository lineRepository;
        public UserRepository driverRepository;
        public BusRepository busRepository;

        public ScheduleController(ScheduleRepository sheduleRepository, LineRepository lineRepository,
                        UserRepository driverRepository, BusRepository busRepository) {
                this.scheduleRepository = sheduleRepository;
                this.lineRepository = lineRepository;
                this.driverRepository = driverRepository;
                this.busRepository = busRepository;
        }

        @GetMapping
        public List<Schedule> getSchedules() {
                return scheduleRepository.findAll();
        }

        @GetMapping("/{id}")
        public Schedule find(@PathVariable Long id) {
                return scheduleRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Schedule não encontrado"));
        }

        // Busca horário por nome ou número da linha
        @GetMapping("/line/{identifier}")
        public List<Schedule> findByLineIdentifier(@PathVariable String identifier) {
                Line line = lineRepository.findByName(identifier)
                                .or(() -> lineRepository.findByNumber(identifier))
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Linha não encontrada"));
                List<Schedule> schedules = scheduleRepository.findByLineId(line.getId());
                if (schedules == null || schedules.isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Nenhum horário encontrado para a linha");
                }
                return schedules;
        }

        @GetMapping("/bus/number/{busNumber}")
        public List<Schedule> findByBusNumber(@PathVariable int busNumber) {
                Bus bus = busRepository.findByNumber(busNumber)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Ônibus não encontrado"));
                List<Schedule> schedules = scheduleRepository.findByBusId(bus.getId());
                if (schedules == null || schedules.isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Nenhum horário encontrado para o ônibus");
                }
                return schedules;
        }

        @PostMapping
        public Schedule save(@RequestBody ScheduleDto scheduleDto) {
                Line line = lineRepository.findById(scheduleDto.lineId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Linha não encontrada"));

                User driver = driverRepository.findById(scheduleDto.driverId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Motorista não encontrado"));

                Bus bus = scheduleDto.busId() != null ? busRepository.findById(scheduleDto.busId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Ônibus não encontrado"))
                                : null;

                Schedule newSchedule = new Schedule(
                                line,
                                scheduleDto.departureTime(),
                                driver,
                                bus);
                return scheduleRepository.save(newSchedule);
        }

        @PutMapping("/{id}")
        public Schedule update(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
                Schedule existingSchedule = scheduleRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Schedule não encontrado"));

                Line line = lineRepository.findById(scheduleDto.lineId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Linha não encontrada"));

                existingSchedule.setLine(line);
                existingSchedule.setDepartureTime(scheduleDto.departureTime());

                return scheduleRepository.save(existingSchedule);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
                Schedule existingSchedule = scheduleRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Schedule não encontrado"));

                scheduleRepository.delete(existingSchedule);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

}
