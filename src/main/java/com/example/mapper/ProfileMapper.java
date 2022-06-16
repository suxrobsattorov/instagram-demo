package com.example.mapper;

import com.example.dto.request.ProfileRequest;
import com.example.dto.response.ProfileResponse;
import com.example.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", expression = "LocalDateTime.now()")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "surname", source = "request.surname")
    @Mapping(target = "username", source = "request.username")
    @Mapping(target = "password", source = "request.password")
    @Mapping(target = "email", source = "request.email")
    ProfileEntity toEntity(ProfileRequest request);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "surname", source = "entity.surname")
    @Mapping(target = "username", source = "entity.username")
    @Mapping(target = "password", source = "entity.password")
    @Mapping(target = "email", source = "entity.email")
    ProfileResponse toResponse(ProfileEntity entity);
}
