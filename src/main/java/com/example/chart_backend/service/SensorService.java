package com.example.chart_backend.service;


import com.example.chart_backend.dto.response.SensorResponseDto;
import com.example.chart_backend.dto.response.SensorValueDto;
import com.example.chart_backend.entity.Sensor;
import com.example.chart_backend.entity.SensorData;
import com.example.chart_backend.repository.SensorDataRepository;
import com.example.chart_backend.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;


     public List<SensorResponseDto> getAllSensorsWithData() {
        List<Sensor> sensors = sensorRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return sensors.stream()
                .map(sensor -> {
                    // group sensor_data của sensor này theo timestamp
                    Map<String, Map<String, Object>> groupedByTime = new LinkedHashMap<>();

                    sensor.getSensorDataList().stream()
                            .sorted(Comparator.comparing(SensorData::getTimestamp))
                            .forEach(d -> {
                                String timeStr = d.getTimestamp().format(formatter);

                                // lấy row theo time, chưa có thì tạo mới
                                Map<String, Object> row = groupedByTime.computeIfAbsent(timeStr, t -> {
                                    Map<String, Object> m = new LinkedHashMap<>();
                                    m.put("time", t);
                                    return m;
                                });

                                String param = d.getParameter(); // Ax, Ay, Temperature,...

                                // nếu muốn đổi tên:
                                // if ("Temperature".equalsIgnoreCase(param)) param = "nhiet";

                                row.put(param, d.getValue());
                            });

                    List<Map<String, Object>> data =
                            new ArrayList<>(groupedByTime.values());

                    return new SensorResponseDto(
                            sensor.getId(),
                            sensor.getSensorName(),
                            data
                    );
                })
                .collect(Collectors.toList());
    }

    // NẾU MUỐN CHỈ LẤY 1 SENSOR THEO ID
    public SensorResponseDto getSensorWithData(Long sensorId) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Map<String, Map<String, Object>> groupedByTime = new LinkedHashMap<>();

        sensor.getSensorDataList().stream()
                .sorted(Comparator.comparing(SensorData::getTimestamp))
                .forEach(d -> {
                    String timeStr = d.getTimestamp().format(formatter);
                    Map<String, Object> row = groupedByTime.computeIfAbsent(timeStr, t -> {
                        Map<String, Object> m = new LinkedHashMap<>();
                        m.put("time", t);
                        return m;
                    });

                    String param = d.getParameter();
                    row.put(param, d.getValue());
                });

        List<Map<String, Object>> data = new ArrayList<>(groupedByTime.values());

        return new SensorResponseDto(sensor.getId(), sensor.getSensorName(), data);
    }
}