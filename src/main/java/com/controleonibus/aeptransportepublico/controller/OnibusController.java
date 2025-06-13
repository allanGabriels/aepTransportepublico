package com.controleonibus.aeptransportepublico.controller;

import com.controleonibus.aeptransportepublico.entity.Onibus;
import com.controleonibus.aeptransportepublico.repository.OnibusRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/onibus")
public class OnibusController {

    public OnibusRepository onibusRepository;

    public OnibusController(OnibusRepository onibusRepository) {
        this.onibusRepository = onibusRepository;
    }

    @GetMapping
    public List<Onibus> listarOnibus(){
        return onibusRepository.findAll();
    }

}
