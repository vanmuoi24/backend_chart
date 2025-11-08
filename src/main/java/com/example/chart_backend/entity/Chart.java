package com.example.chart_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.databind.JsonNode; // dùng Jackson để map JSON tự động

@Entity
@Table(name = "chart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // tên biểu đồ
    private String type; // loại (stress, temperature, humidity...)

    // Lưu data dạng JSON string trong database
    @Column(columnDefinition = "JSON")
    private String data;
}
