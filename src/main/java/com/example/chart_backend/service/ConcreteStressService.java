package com.example.chart_backend.service;



import org.springframework.stereotype.Service;

import com.example.chart_backend.entity.ConcreteStress;
import com.example.chart_backend.repository.ConcreteStressRepository;

import java.util.List;

@Service
public class ConcreteStressService {

    private final ConcreteStressRepository repository;

    public ConcreteStressService(ConcreteStressRepository repository) {
        this.repository = repository;
    }

    public List<ConcreteStress> getAllData() {
        return repository.findAll();
    }

    public ConcreteStress save(ConcreteStress stress) {
        return repository.save(stress);
    }
}
