package com.controleonibus.aeptransportepublico.controller;

import com.controleonibus.aeptransportepublico.entity.Bus;
import com.controleonibus.aeptransportepublico.repository.BusRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
