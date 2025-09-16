package com.lgcns.studify_be.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lgcns.studify_be.post.domain.entity.PostEntity;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    
    List<PostEntity> findByTitleContainingIgnoreCase(String keyword);
    List<PostEntity> findByContentContainingIgnoreCase(String keyword);
    List<PostEntity> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String titleKeyword, String contentKeyword);

}
