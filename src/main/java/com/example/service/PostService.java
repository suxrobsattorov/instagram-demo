package com.example.service;

import com.example.dto.request.PostRequest;
import com.example.entity.PostEntity;
import org.springframework.http.ResponseEntity;

public interface PostService {

    ResponseEntity<?> save(PostRequest request);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(Long id);

    PostEntity getById(Long id);
}
