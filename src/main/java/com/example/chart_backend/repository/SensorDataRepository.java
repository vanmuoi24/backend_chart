package com.example.chart_backend.repository;

import com.example.chart_backend.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findBySensorId(Long sensorId);

    List<SensorData> findAllByOrderByTimestampAsc();
}