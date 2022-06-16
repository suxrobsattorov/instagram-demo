package com.example.mapper.impl;

import com.example.dto.request.ProfileRequest;
import com.example.dto.response.ProfileResponse;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.mapper.ProfileMapper;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-06-02T02:11:05+0500",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileEntity toEntity(ProfileRequest request) {
        if (request == null) {
            return null;
        }
        ProfileEntity entity = new ProfileEntity();

        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setRole(request.getRole());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    @Override
    public ProfileResponse toResponse(ProfileEntity entity) {
        if (entity == null) {
            return null;
        }
        ProfileResponse.ProfileResponseBuilder response = ProfileResponse.builder();

        response.id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt());
        return response.build();
    }
}
