package com.lgcns.studify_be.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lgcns.studify_be.comment.domain.entity.CommentEntity;


public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    public List<CommentEntity> findByPost_PostId(Integer postId);
} 
