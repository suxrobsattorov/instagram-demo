package com.example.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PostRequest {
    private String title;
    private String addPeople;
    private String addLocation;
    private Long profileId;
    private List<Long> attachList;
}
