package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AttachDTO {
    private Long id;
    private String key;
    private String originName;
    private Long size;
    private String filePath;
    private String extension;
    private LocalDateTime createdAt;
    private String url;
}
