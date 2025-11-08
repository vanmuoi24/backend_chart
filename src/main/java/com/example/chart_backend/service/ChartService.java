package com.example.chart_backend.service;



import com.corundumstudio.socketio.SocketIOServer;
import com.example.chart_backend.entity.Chart;
import com.example.chart_backend.repository.ChartRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChartService {

    private final ChartRepository chartRepository;
private final SocketIOServer socketIOServer;
    public ChartService(ChartRepository chartRepository ,SocketIOServer socketIOServer) {
        this.chartRepository = chartRepository;
        this.socketIOServer=socketIOServer;
    }

    public List<Chart> getAllCharts() {
        return chartRepository.findAll();
    }

    public Optional<Chart> getChartById(Long id) {
        return chartRepository.findById(id);
    }

    public Chart createChart(Chart chart) {
        return chartRepository.save(chart);
    }

    public void deleteChart(Long id) {
        chartRepository.deleteById(id);
    }
    
    public Chart updateChart(Long id, Chart chartDetails) {
        Chart chart = chartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chart with ID " + id + " not found"));
        chart.setName(chartDetails.getName());
        chart.setType(chartDetails.getType());
        chart.setData(chartDetails.getData());
        Chart updated = chartRepository.save(chart);
        socketIOServer.getBroadcastOperations().sendEvent("chart_update", updated);
        return chartRepository.save(chart);
    }
}
