package com.lgcns.studify_be.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lgcns.studify_be.post.domain.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    
}
