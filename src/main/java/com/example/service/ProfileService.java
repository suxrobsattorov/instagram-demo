package com.example.service;

import com.example.dto.request.ProfileRequest;
import com.example.entity.ProfileEntity;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

    ResponseEntity<?> save(ProfileRequest request);

    ResponseEntity<?> edit(ProfileRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(Long id);

    ProfileEntity getById(Long id);
}
