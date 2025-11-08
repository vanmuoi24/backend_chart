package com.example.chart_backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "concrete_stress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcreteStress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "measure_date")
    private String date; // hoặc LocalDate nếu bạn dùng format chuẩn

    @Column(name = "dung_doc")
    private Double dungdoc;

    @Column(name = "ngang_cau")
    private Double ngangcau;

    @Column(name = "truc_vuong")
    private Double trucvuong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chart_id")
    private Chart chart;
}
