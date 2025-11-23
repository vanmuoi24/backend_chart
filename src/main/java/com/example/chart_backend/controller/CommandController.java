package com.example.chart_backend.controller;

import com.example.chart_backend.dto.request.CommandRequest;
import com.example.chart_backend.dto.response.CommandResponse;
import com.example.chart_backend.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommandController {

    private final CommandService commandService;

    @PostMapping
    public ResponseEntity<CommandResponse> create(@RequestBody CommandRequest request) {
        return ResponseEntity.ok(commandService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CommandResponse>> getAll() {
        return ResponseEntity.ok(commandService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(commandService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandResponse> update(@PathVariable Long id,
            @RequestBody CommandRequest request) {
        return ResponseEntity.ok(commandService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
