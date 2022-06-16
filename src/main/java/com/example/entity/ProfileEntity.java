package com.example.entity;

import com.example.entity.template.AbcEntity;
import com.example.enums.ProfileStatus;
import com.example.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profile")
public class ProfileEntity extends AbcEntity {

    private String name;

    private String surname;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

}
