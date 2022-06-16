package com.example.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String addPeople;
    private String addLocation;
    private Long profileId;
    private LocalDateTime createdAt;
    private List<Long> attachList;
}
