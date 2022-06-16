package com.example.dto.response;

import com.example.enums.ProfileStatus;
import com.example.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Role role;
    private ProfileStatus status;
    private LocalDateTime createdAt;
}
