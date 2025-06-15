package com.controleonibus.aeptransportepublico.controller;

import com.controleonibus.aeptransportepublico.entity.Bus;
import com.controleonibus.aeptransportepublico.repository.BusRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.controleonibus.aeptransportepublico.dto.BusDto;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    public BusRepository busRepository;

    public BusController(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @GetMapping
    public List<Bus> list() {
        return busRepository.findAll();
    }

    @GetMapping("/{id}")
    public Bus find(@PathVariable Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Onibus não encontrado"));
    }

    @PostMapping
    public Bus save(@RequestBody BusDto busDto) {
        Bus newBus = new Bus(
                busDto.number(),
                busDto.seats(),
                busDto.electric());

        return busRepository.save(newBus);
    }

    @PutMapping("/{id}")
    public Bus update(@PathVariable Long id, @RequestBody BusDto busDto) {
        Bus existingBus = busRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Onibus não encontrado"));

        existingBus.setNumber(busDto.number());
        existingBus.setSeats(busDto.seats());
        existingBus.setElectric(busDto.electric());
        return busRepository.save(existingBus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!busRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Onibus não encontrado");
        }
        busRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
