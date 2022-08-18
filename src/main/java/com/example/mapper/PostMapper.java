package com.example.mapper;

import com.example.dto.request.PostRequest;
import com.example.dto.response.PostResponse;
import com.example.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface PostMapper {

    PostEntity toEntity( PostRequest request );

    PostResponse toResponse( PostEntity entity );
}
