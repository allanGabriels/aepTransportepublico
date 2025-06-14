package com.controleonibus.aeptransportepublico.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.controleonibus.aeptransportepublico.dto.LineDto;
import com.controleonibus.aeptransportepublico.entity.Line;
import java.util.List;

import com.controleonibus.aeptransportepublico.repository.LineRepository;

@RestController
@RequestMapping("/line")
public class LineController {

    public LineRepository lineRepository;

    public LineController(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    @GetMapping
    public List<Line> list() {
        return lineRepository.findAll();
    }

    @GetMapping("{id}")
    public Line find(Long id) {
        return lineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Linha não encontrada"));
    }

    @PostMapping
    public Line save(@RequestBody LineDto lineDto) {
        Line newLine = new Line(
                lineDto.number(),
                lineDto.name());
        return lineRepository.save(newLine);
    }

    @PutMapping("{id}")
    public Line update(@PathVariable Long id, @RequestBody LineDto lineDto) {
        Line existingLine = lineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Linha não encontrada"));

        existingLine.setNumber(lineDto.number());
        existingLine.setName(lineDto.name());

        return lineRepository.save(existingLine);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Line existingLine = lineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Linha não encontrada"));

        lineRepository.delete(existingLine);
        return ResponseEntity.noContent().build();
    }

}
