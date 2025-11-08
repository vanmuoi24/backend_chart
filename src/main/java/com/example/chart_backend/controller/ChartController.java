package com.example.chart_backend.controller;

import com.example.chart_backend.entity.Chart;
import com.example.chart_backend.service.ChartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts")
@CrossOrigin(origins = "*")
public class ChartController {

    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    // 🟢 Lấy toàn bộ danh sách biểu đồ
    @GetMapping
    public List<Chart> getAllCharts() {
        return chartService.getAllCharts();
    }

    // 🟢 Lấy biểu đồ theo ID
    @GetMapping("/{id}")
    public Chart getChartById(@PathVariable Long id) {
        return chartService.getChartById(id)
                .orElseThrow(() -> new RuntimeException("Chart with ID " + id + " not found"));
    }

    // 🟢 Tạo mới biểu đồ
    @PostMapping
    public Chart createChart(@RequestBody Chart chart) {
        return chartService.createChart(chart);
    }

    // 🟢 Xóa biểu đồ theo ID
    @DeleteMapping("/{id}")
    public void deleteChart(@PathVariable Long id) {
        chartService.deleteChart(id);
    }

    @PutMapping("/{id}")
    public Chart updateChart(@PathVariable Long id, @RequestBody Chart chart) {
        return chartService.updateChart(id, chart);
    }
}
