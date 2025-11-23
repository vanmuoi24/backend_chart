package com.example.chart_backend.repository;

import com.example.chart_backend.entity.Sensor;
import com.example.chart_backend.entity.Topic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}