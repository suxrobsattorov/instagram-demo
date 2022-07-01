package com.example.service.impl;

import com.example.dto.Response;
import com.example.dto.request.CommentRequest;
import com.example.dto.response.CommentResponse;
import com.example.entity.CommentEntity;
import com.example.mapper.CommentMapper;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import com.example.service.base.GetById;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Suxrob Sattorov, Fri 11:17 PM. 7/1/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService, GetById<CommentEntity, Long> {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Override
    public ResponseEntity<?> save( CommentRequest request ) {
        CommentResponse response = commentMapper.toResponse(
                commentRepository.save(commentMapper.toEntity(request))
        );
        return ResponseEntity.status(201).body(
                new Response(
                        "Comment successfully added !!!",
                        true,
                        response
                )
        );
    }

    @Override
    public ResponseEntity<?> get( Long id ) {

        CommentEntity entity = getById(id);

        CommentResponse response = commentMapper.toResponse(entity);
        return ResponseEntity.status(201).body(
                new Response(
                        "Comment successfully gel by id !!!",
                        true,
                        response
                )
        );
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<CommentResponse> responseList = new LinkedList<>();
        for ( CommentEntity commentEntity : commentRepository.findAll() ) {
            responseList.add(commentMapper.toResponse(commentEntity));
        }
        return ResponseEntity.status(201).body(
                new Response(
                        "Comment successfully gel All !!!",
                        true,
                        responseList
                )
        );
    }

    @Override
    public ResponseEntity<?> delete( Long id ) {
        commentRepository.delete(getById(id));
        return ResponseEntity.status(204).body(new Response(
                "Comment successfully deleted !!!",
                true));
    }

    @Override
    public CommentEntity getById( Long id ) {
        return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
    }
}
