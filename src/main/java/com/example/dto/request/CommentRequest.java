package com.example.dto.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Long profileId;
}
