package com.example.entity;

import com.example.entity.template.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author Suxrob Sattorov, Fri 10:46 PM. 7/1/2022
 */
@Data
@Entity
@Table( name = "comment" )
public class CommentEntity extends BaseEntity {

    private String content;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "post_id" )
    private PostEntity post;

//    @ManyToOne( fetch = FetchType.LAZY )
//    @JoinColumn( name = "profile_id" )
//    private ProfileEntity profile;

    private Long profileId;

}
