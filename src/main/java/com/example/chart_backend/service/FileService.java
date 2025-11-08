package com.example.chart_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.chart_backend.entity.FileEntity;
import com.example.chart_backend.repository.FileRepository;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileService {
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private FileRepository fileRepository;

    public FileEntity uploadFile(MultipartFile file) throws IOException {
        Files.createDirectories(Paths.get(UPLOAD_DIR));

        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, file.getBytes());

        FileEntity entity = FileEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .size(String.format("%.2f MB", (double) file.getSize() / (1024 * 1024)))
                .status("available")
                .path(path.toString())
                .createdAt(LocalDateTime.now())
                .build();

        return fileRepository.save(entity);
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    public List<FileEntity> searchFiles(String keyword) {
        return fileRepository.findByNameContainingIgnoreCase(keyword);
    }

    public void deleteFile(Long id) throws IOException {
        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Files.deleteIfExists(Paths.get(file.getPath()));
        fileRepository.delete(file);
    }
    
    public FileEntity getFileById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

}
