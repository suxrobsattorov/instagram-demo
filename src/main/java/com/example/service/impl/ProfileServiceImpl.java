package com.example.service.impl;

import com.example.dto.Response;
import com.example.dto.request.ProfileRequest;
import com.example.dto.response.ProfileResponse;
import com.example.entity.ProfileEntity;
import com.example.mapper.ProfileMapper;
import com.example.repository.ProfileRepository;
import com.example.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;


    @Override
    public ResponseEntity<?> save(ProfileRequest request) {
        ProfileResponse response = profileMapper.toResponse(
                profileRepository.save(profileMapper.toEntity(request))
        );
        return ResponseEntity.status(201).body(
                new Response(
                        "Profile successfully added !!!",
                        true,
                        response
                )
        );
    }

    @Override
    public ResponseEntity<?> edit(ProfileRequest request, Long id) {

        ProfileEntity entity = profileRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Profile id not found"));

        if (!entity.getName().equals(request.getName()) && request.getName() != null)
            entity.setName(request.getName());

        if (!entity.getSurname().equals(request.getSurname()) && request.getSurname() != null)
            entity.setSurname(request.getSurname());

        if (!entity.getUsername().equals(request.getUsername()) && request.getUsername() != null)
            entity.setUsername(request.getUsername());

        if (!entity.getEmail().equals(request.getEmail()) && request.getEmail() != null)
            entity.setEmail(request.getEmail());

        if (!entity.getPassword().equals(request.getPassword()) && request.getPassword() != null)
            entity.setPassword(request.getPassword());

        if (!entity.getRole().equals(request.getRole()) && request.getRole() != null)
            entity.setRole(request.getRole());

        ProfileResponse response = profileMapper.toResponse(
                profileRepository.save(
                        entity)
        );
        return ResponseEntity.status(201).body(
                new Response(
                        "Profile successfully edited !!!",
                        true,
                        response));
    }

    @Override
    public ResponseEntity<?> get(Long id) {

        ProfileEntity entity = profileRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Profile id not found"));

        ProfileResponse response = profileMapper.toResponse(entity);
        return ResponseEntity.status(201).body(
                new Response(
                        "Profile successfully gel by id !!!",
                        true,
                        response
                )
        );
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(201).body(
                new Response(
                        "Profile successfully gel All !!!",
                        true,
                        profileRepository.findAll()
                )
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        profileRepository.delete(profileRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Profile id not found")));
        return ResponseEntity.status(204).body(new Response(
                "Profile successfully deleted !!!",
                true));
    }

    @Override
    public ProfileEntity getById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile id not found"));
    }
}
