package com.example.service.impl;

import com.example.dto.AttachDTO;
import com.example.dto.Response;
import com.example.entity.AttachEntity;
import com.example.repository.AttachRepository;
import com.example.service.AttachService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachServiceImpl implements AttachService {

    private final AttachRepository attachRepository;

    @Value("${attach.upload.folder}")
    private String uploadFolder; // uploads

    @Value("${attach.open.url}")
    private String attachOpenUrl;

    @Override
    public ResponseEntity<?> saveFile(MultipartFile file) {

        String filePath = getYMDString();
        String key = UUID.randomUUID().toString();
        String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));

        File folder = new File(uploadFolder + "/" + filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolder + "/" + filePath + "/" + key + "." + extension);
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setKey(key);
            entity.setExtension(extension);
            entity.setOriginName(file.getOriginalFilename());
            entity.setSize(file.getSize());
            entity.setFilePath(filePath);
            entity.setCreatedAt(LocalDateTime.now());
            attachRepository.save(entity);

            return ResponseEntity.status(201).body(
                    new Response(
                            "Attach successfully added !!!",
                            true,
                            toDto(entity)
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] loadAttach(String key) {
        byte[] imageInByte;
        Optional<AttachEntity> optional = attachRepository.findByKey(key);
        if (!optional.isPresent()) {
            return new byte[0];
        }
        BufferedImage originalImage;
        String filePath = optional.get().getFilePath() + "/" + key + "." + optional.get().getExtension();
        try {
            originalImage = ImageIO.read(new File(uploadFolder + "/" + filePath));
        } catch (Exception e) {
            return new byte[0];
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(originalImage, optional.get().getExtension(), baos);

            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] loadGeneral(String key) {
        Optional<AttachEntity> optional = attachRepository.findByKey(key);
        if (!optional.isPresent()) {
            return null;
        }
        String filePath = optional.get().getFilePath() + "/" + key + "." + optional.get().getExtension();
        File file = new File(uploadFolder + "/" + filePath);
        if (file.exists()) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                return inputStream.readAllBytes();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public Resource load(String fileName) {
        try {
            Path file = Paths.get(uploadFolder + "/" + fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> delete(String key) {
        Optional<AttachEntity> optional = attachRepository.findByKey(key);
        if (!optional.isPresent()) {
            return null;
        }
        String filePath = optional.get().getFilePath() + "/" + key + "." + optional.get().getExtension();
        File file = new File(uploadFolder + "/" + filePath);
        if (file.exists()) {
            file.delete();
        }
        attachRepository.delete(optional.get());
        return ResponseEntity.status(204).body(new Response(
                "Attach successfully deleted !!!",
                true));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        return ResponseEntity.status(201).body(attachRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attach not found")));
    }

    @Override
    public List<AttachEntity> getAll() {
        return attachRepository.findAll();
    }

    public String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public static String getYMDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day + "/";
    }

    public AttachDTO toDto(AttachEntity entity) {
        AttachDTO attachDTO = new AttachDTO();
        attachDTO.setId(entity.getId());
        attachDTO.setKey(entity.getKey());
        attachDTO.setFilePath(entity.getFilePath());
        attachDTO.setCreatedAt(entity.getCreatedAt());
        attachDTO.setSize(entity.getSize());
        attachDTO.setOriginName(entity.getOriginName());
        attachDTO.setExtension(entity.getExtension());
        attachDTO.setUrl(attachOpenUrl + "/" + entity.getKey());
        return attachDTO;
    }
}
