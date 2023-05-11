package com.questapp.repository;

import com.questapp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByPostIdAndUserId(Long postId, Long userId);

    List<Like> findByPostId(Long postId);

    List<Like> findByUserId(Long userId);
}
