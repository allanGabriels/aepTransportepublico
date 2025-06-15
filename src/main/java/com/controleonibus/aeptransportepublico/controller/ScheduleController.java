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
import com.controleonibus.aeptransportepublico.entity.Schedule;
import com.controleonibus.aeptransportepublico.dto.ScheduleDto;
import com.controleonibus.aeptransportepublico.entity.Line;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    public ScheduleRepository scheduleRepository;
    public LineRepository lineRepository;

    public ScheduleController(ScheduleRepository sheduleRepository, LineRepository lineRepository) {
        this.scheduleRepository = sheduleRepository;
        this.lineRepository = lineRepository;
    }

    @GetMapping
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Schedule find(@PathVariable Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule não encontrado"));
    }

    @PostMapping
    public Schedule save(@RequestBody ScheduleDto scheduleDto) {
        Line line = lineRepository.findById(scheduleDto.lineId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Linha não encontrada"));

        Schedule newSchedule = new Schedule(
                line,
                scheduleDto.departureTime());
        return scheduleRepository.save(newSchedule);
    }

    @PutMapping("/{id}")
    public Schedule update(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule não encontrado"));

        Line line = lineRepository.findById(scheduleDto.lineId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Linha não encontrada"));

        existingSchedule.setLine(line);
        existingSchedule.setDepartureTime(scheduleDto.departureTime());

        return scheduleRepository.save(existingSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule não encontrado"));

        scheduleRepository.delete(existingSchedule);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
