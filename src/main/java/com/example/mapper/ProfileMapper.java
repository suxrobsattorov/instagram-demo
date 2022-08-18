package com.example.mapper;

import com.example.dto.request.ProfileRequest;
import com.example.dto.response.ProfileResponse;
import com.example.entity.ProfileEntity;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface ProfileMapper {

    ProfileEntity toEntity(ProfileRequest request);

    ProfileResponse toResponse(ProfileEntity entity);
}
