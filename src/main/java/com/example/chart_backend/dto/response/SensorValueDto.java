package com.example.chart_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorValueDto {
    private String parameter;
    private Double value;
    private String timestamp;
}