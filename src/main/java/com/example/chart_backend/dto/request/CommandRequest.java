package com.example.chart_backend.dto.request;

import lombok.Data;

@Data
public class CommandRequest {
    private Long topicId;
    private String func;
    private String actions;
    private Double processed;
}
