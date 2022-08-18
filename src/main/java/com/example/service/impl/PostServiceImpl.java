package com.example.service.impl;

import com.example.dto.Response;
import com.example.dto.request.PostRequest;
import com.example.dto.response.PostResponse;
import com.example.entity.PostEntity;
import com.example.mapper.PostMapper;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import com.example.service.base.GetById;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService, GetById<PostEntity, Long> {

    private final PostRepository postRepository;

    private final PostMapper postMapper;


    @Override
    public ResponseEntity<?> save( PostRequest request ) {
        PostResponse response = postMapper.toResponse(
                postRepository.save(postMapper.toEntity(request))
        );
        return ResponseEntity.status(201).body(
                new Response(
                        "Post successfully added !!!",
                        true,
                        response
                )
        );
    }

    @Override
    public ResponseEntity<?> get( Long id ) {

        PostEntity entity = getById(id);

        PostResponse response = postMapper.toResponse(entity);
        return ResponseEntity.status(201).body(
                new Response(
                        "Post successfully gel by id !!!",
                        true,
                        response
                )
        );
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<PostResponse> responseList = new LinkedList<>();
        for ( PostEntity postEntity : postRepository.findAll() ) {
            responseList.add(postMapper.toResponse(postEntity));
        }
        return ResponseEntity.status(201).body(
                new Response(
                        "Post successfully gel All !!!",
                        true,
                        responseList
                )
        );
    }

    @Override
    public ResponseEntity<?> delete( Long id ) {
        postRepository.delete(getById(id));
        return ResponseEntity.status(204).body(new Response(
                "Post successfully deleted !!!",
                true));
    }

    @Override
    public PostEntity getById( Long id ) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post id not found"));
    }
}
