package com.example.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private Long profileId;
    private Long postId;
    private LocalDateTime createdAt;
}
