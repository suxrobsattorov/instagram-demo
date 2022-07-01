package com.example.repository;

import com.example.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Suxrob Sattorov, Fri 10:53 PM. 7/1/2022
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
