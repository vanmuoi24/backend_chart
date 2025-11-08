package com.example.chart_backend.repository;


import com.example.chart_backend.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartRepository extends JpaRepository<Chart, Long> {
}
