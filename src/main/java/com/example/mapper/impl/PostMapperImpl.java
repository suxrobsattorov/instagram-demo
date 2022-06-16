package com.example.mapper.impl;

import com.example.dto.request.PostRequest;
import com.example.dto.response.PostResponse;
import com.example.entity.AttachEntity;
import com.example.entity.PostEntity;
import com.example.mapper.PostMapper;
import com.example.service.AttachService;
import com.example.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.*;

@Generated(
        value = "ord.mapstruct.ap.MappingProcessor",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
@RequiredArgsConstructor
public class PostMapperImpl implements PostMapper {

    private final ProfileService profileService;

    private final AttachService attachService;

    @Override
    public PostEntity toEntity(PostRequest request) {
        if (request == null) {
            return null;
        }
        Set<AttachEntity> attachEntitySet = new HashSet<>();
        for (AttachEntity attachEntity : attachService.getAll()) {
            for (Long id : request.getAttachList()) {
                if (attachEntity.getId().equals(id)) {
                    attachEntitySet.add(attachEntity);
                }
            }
        }
        PostEntity entity = new PostEntity();
        entity.setTitle(request.getTitle());
        entity.setAddPeople(request.getAddPeople());
        entity.setAddLocation(request.getAddLocation());
        entity.setProfile(profileService.getById(request.getProfileId()));
        entity.setAttach(attachEntitySet);
        return entity;
    }

    @Override
    public PostResponse toResponse(PostEntity entity) {
        if (entity == null) {
            return null;
        }
        List<Long> attachEntitySet = new LinkedList<>();
        for (AttachEntity attachEntity : attachService.getAll()) {
            for (AttachEntity attachEntity1 : entity.getAttach()) {
                if (attachEntity.getId().equals(attachEntity1.getId())) {
                    attachEntitySet.add(attachEntity.getId());
                }
            }
        }
        PostResponse.PostResponseBuilder response = PostResponse.builder();
        response.id(entity.getId())
                .title(entity.getTitle())
                .addPeople(entity.getAddPeople())
                .addLocation(entity.getAddLocation())
                .profileId(entity.getProfile().getId())
                .attachList(attachEntitySet);
        return response.build();
    }
}
