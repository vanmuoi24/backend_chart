package com.example.chart_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "commands") // 👉 Nếu tên bảng khác (vd: cmd, cmds, command_queue) thì sửa lại
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmd_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    @JsonBackReference
    private Topic topic;

    @Column(name = "func")
    private String func; 

    @Column(name = "actions")
    private String actions;

    @Column(name = "processed")
    private Double processed; 
}