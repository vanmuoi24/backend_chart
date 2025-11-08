package com.example.chart_backend.controller;
import org.springframework.web.bind.annotation.*;

import com.example.chart_backend.entity.ConcreteStress;
import com.example.chart_backend.service.ConcreteStressService;

import java.util.List;

@RestController
@RequestMapping("/api/concrete-stress")

public class ConcreteStressController {

    private final ConcreteStressService service;

    public ConcreteStressController(ConcreteStressService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConcreteStress> getAll() {
        return service.getAllData();
    }

    @PostMapping
    public ConcreteStress addNew(@RequestBody ConcreteStress stress) {
        return service.save(stress);
    }
}
