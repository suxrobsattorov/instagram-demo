package com.example.service;

import com.example.entity.AttachEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachService {

    ResponseEntity<?> saveFile(MultipartFile file);

    byte[] loadAttach(String key);

    byte[] loadGeneral(String fileName);

    Resource load(String fileName);

    ResponseEntity<?> delete(String key);

    ResponseEntity<?> get(Long id);

    List<AttachEntity> getAll();
}
