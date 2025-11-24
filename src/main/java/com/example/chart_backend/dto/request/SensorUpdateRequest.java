package com.example.chart_backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorUpdateRequest {
    private String sensorName;
    private String unit;
}