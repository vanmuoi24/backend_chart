package com.example.chart_backend.repository;

import com.example.chart_backend.entity.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommandRepository extends JpaRepository<Command, Long> {

    List<Command> findByTopicId(Long topicId);
}