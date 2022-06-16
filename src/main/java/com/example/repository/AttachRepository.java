package com.example.repository;

import com.example.entity.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachRepository extends JpaRepository<AttachEntity, Long> {
    Optional<AttachEntity> findByKey(String key);
}
