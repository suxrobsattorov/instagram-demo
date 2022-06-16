package com.example.entity;

import com.example.entity.template.AbcEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "post")
public class PostEntity extends AbcEntity {

    private String title;

    private String addPeople;

    private String addLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @ManyToMany(targetEntity = AttachEntity.class,cascade = CascadeType.ALL )
    private Set<AttachEntity> attach;
}
