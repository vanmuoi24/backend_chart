package com.example.chart_backend.dto.response;

import com.example.chart_backend.entity.Topic;

import lombok.Data;

@Data
public class CommandResponse {

    private Long id;
    private Topic topic;
    private String func;
    private String actions;
    private Double processed;
}
