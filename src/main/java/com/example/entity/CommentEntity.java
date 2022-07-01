package com.example.entity;

import com.example.entity.template.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author Suxrob Sattorov, Fri 10:46 PM. 7/1/2022
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {

    private String content;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "profile_id" )
    private ProfileEntity profile;

}
