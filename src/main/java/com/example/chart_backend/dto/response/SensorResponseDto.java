package com.example.chart_backend.dto.response;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorResponseDto {
    private Long id; // sensor_id
    private String name; // sensor_name
    private String unit; // đơn vị đo
    private List<Map<String, Object>> data; // mỗi map: { time, Ax, Ay, ... }
}
