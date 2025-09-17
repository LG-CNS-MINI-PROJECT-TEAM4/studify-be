package com.lgcns.studify_be.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgcns.studify_be.post.domain.dto.PostRequestDTO;
import com.lgcns.studify_be.post.domain.dto.PostResponseDTO;
import com.lgcns.studify_be.post.domain.entity.PostEntity;
import com.lgcns.studify_be.post.domain.entity.PostStatus;
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

    public PostResponseDTO readPostDetail(Integer postId) {
        // PostEntity post = postRepository.findByIdWithComments(postId)
        //             .orElseThrow(() -> new RuntimeException("존재하지 않는 모집글"));
        // return PostResponseDTO.fromEntity(post);
        return null;
    }

    public List<PostResponseDTO> findPostByTitle(String keyword) {
        List<PostEntity> postList = postRepository.findByTitleContainingIgnoreCase(keyword);
        return postList.stream()
                .map(entity -> PostResponseDTO.fromEntity(entity))
                .toList();
    }

    public List<PostResponseDTO> findPostByContent(String keyword) {
        List<PostEntity> postList = postRepository.findByContentContainingIgnoreCase(keyword);
        return postList.stream()
                .map(entity -> PostResponseDTO.fromEntity(entity))
                .toList();
    }

    public List<PostResponseDTO> findPostByTitleContent(String keyword) {
        List<PostEntity> postList = postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        return postList.stream()
                .map(entity -> PostResponseDTO.fromEntity(entity))
                .toList();
    }

    @Transactional
    public PostResponseDTO updatePost(Integer postId, PostRequestDTO request) {
        // author 조회
        PostEntity post = postRepository.findById(postId)
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 모집글"));
        post.update(request);
        return PostResponseDTO.fromEntity(post);
    }

    public void deletePost(Integer postId) {
        PostEntity post = postRepository.findById(postId)
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 모집글"));
        postRepository.delete(post);
    }

    public PostResponseDTO closePost(Integer postId) {
        PostEntity post = postRepository.findById(postId)
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 모집글"));
        post.setStatus(PostStatus.CLOSED);
        postRepository.save(post);
        return PostResponseDTO.fromEntity(post);
    }
}
