package com.example.chart_backend.controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.chart_backend.dto.response.SensorResponseDto;
import com.example.chart_backend.service.SensorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sensors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SensorController {

    private final SensorService sensorService;

    // Lấy tất cả sensor + data gom theo time
    @GetMapping("")
    public ResponseEntity<List<SensorResponseDto>> getAllSensorsWithData() {
        return ResponseEntity.ok(sensorService.getAllSensorsWithData());
    }

    // Lấy 1 sensor theo id
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDto> getSensorWithData(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.getSensorWithData(id));
    }
}
