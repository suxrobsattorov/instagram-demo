package com.example.dto.request;
import com.example.enums.Role;
import lombok.Data;

@Data
public class ProfileRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Role role;
}
