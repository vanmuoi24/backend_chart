package com.example.chart_backend.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chart_backend.entity.FileEntity;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByNameContainingIgnoreCase(String keyword);
}
