package com.example.mapper;

import com.example.dto.request.CommentRequest;
import com.example.dto.response.CommentResponse;
import com.example.entity.CommentEntity;
import org.mapstruct.Mapper;

/**
 * @author Suxrob Sattorov, Fri 10:51 PM. 7/1/2022
 */
@Mapper( componentModel = "spring" )
public interface CommentMapper {

    CommentEntity toEntity( CommentRequest request );

    CommentResponse toResponse( CommentEntity entity );
}
