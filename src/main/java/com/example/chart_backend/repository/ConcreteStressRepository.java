package com.example.chart_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chart_backend.entity.ConcreteStress;

@Repository
public interface ConcreteStressRepository extends JpaRepository<ConcreteStress, Long> {
}