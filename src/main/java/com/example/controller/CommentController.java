package com.example.controller;

import com.example.dto.request.CommentRequest;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping( "/api/v1/comment" )
public class CommentController {

    private final CommentService commentService;

    @PostMapping( "/save" )
    public ResponseEntity<?> save( @RequestBody CommentRequest request ) {
        return ResponseEntity.status(201).body(commentService.save(request));
    }

    @GetMapping( "/get/{id}" )
    public ResponseEntity<?> get( @PathVariable Long id ) {
        return ResponseEntity.status(200).body(commentService.get(id));
    }

    @GetMapping( "/get" )
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(commentService.getAll());
    }

    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<?> delete( @PathVariable Long id ) {
        return ResponseEntity.status(204).body(commentService.delete(id));
    }
}
