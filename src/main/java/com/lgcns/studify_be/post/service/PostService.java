package com.lgcns.studify_be.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgcns.studify_be.post.domain.dto.PostRequestDTO;
import com.lgcns.studify_be.post.domain.dto.PostResponseDTO;
import com.lgcns.studify_be.post.domain.entity.PostEntity;
import com.lgcns.studify_be.post.repository.PostRepository;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    public PostResponseDTO register(PostRequestDTO request) {
        // author 조회
        PostEntity post = postRepository.save(request.toEntity());
        return PostResponseDTO.fromEntity(post);
    }

    public List<PostResponseDTO> readPostList() {
        List<PostEntity> postList = postRepository.findAll();
        return postList.stream()
                        .map(entity -> PostResponseDTO.fromEntity(entity))
                        .toList();     
    }

}
