package com.example.entity;

import com.example.entity.template.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "attach")
public class AttachEntity extends BaseEntity {

    private String key;

    private String originName;

    private Long size;

    private String filePath;

    private String extension;

    @ManyToMany(targetEntity = PostEntity.class, mappedBy = "attach", cascade = CascadeType.ALL)
    private Set<PostEntity> post;
}
