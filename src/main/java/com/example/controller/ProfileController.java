package com.example.controller;

import com.example.dto.request.ProfileRequest;
import com.example.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProfileRequest request) {
        return ResponseEntity.status(201).body(profileService.save(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(profileService.get(id));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(profileService.getAll());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody ProfileRequest request, @PathVariable Long id) {
        return ResponseEntity.status(203).body(profileService.edit(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(204).body(profileService.delete(id));
    }
}
