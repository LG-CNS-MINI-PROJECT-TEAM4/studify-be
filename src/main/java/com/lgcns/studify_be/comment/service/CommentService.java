package com.lgcns.studify_be.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgcns.studify_be.comment.domain.dto.CommentRequestDTO;
import com.lgcns.studify_be.comment.domain.dto.CommentResponseDTO;
import com.lgcns.studify_be.comment.domain.entity.CommentEntity;
import com.lgcns.studify_be.comment.repository.CommentRepository;
import com.lgcns.studify_be.post.domain.entity.PostEntity;
import com.lgcns.studify_be.post.repository.PostRepository;
import com.lgcns.studify_be.user.User;
import com.lgcns.studify_be.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<CommentResponseDTO> createComment(Long postId, CommentRequestDTO req) {
        System.out.println(">>> CommentService - createComment");

        PostEntity post = postRepository.findById(postId)
                            .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

        // TODO: 나중에 인증된 사용자 정보로 변경
        User tempUser = userRepository.findById(2L)
                            .orElseThrow(() -> new RuntimeException("임시 사용자를 찾을 수 없습니다."));

        CommentEntity comment = CommentEntity.builder()
                                    .content(req.getContent())
                                    .post(post)
                                    .user(tempUser)
                                    .build();

        commentRepository.save(comment);

        // 전체 댓글 목록 반환
        List<CommentEntity> comments = commentRepository.findByPost_PostId(postId);
        List<CommentResponseDTO> commentDTOs = comments.stream()
                                            .map(CommentResponseDTO::fromEntity)
                                            .toList();
        return commentDTOs;
    }

    @Transactional
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO req) {
        System.out.println(">>> CommentService - updateComment");

        CommentEntity comment = commentRepository.findById(commentId)
                                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다."));

        // TODO: 나중에 인증된 사용자 정보로 변경                        
        User tempUser = userRepository.findById(2L)
                            .orElseThrow(() -> new RuntimeException("임시 사용자를 찾을 수 없습니다."));
        
        if(!comment.getUser().getId().equals(tempUser.getId())) {
            throw new RuntimeException("댓글 수정 권한이 없습니다.");
        }

        comment.setContent(req.getContent());

        return CommentResponseDTO.fromEntity(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        System.out.println(">>> CommentService - deleteComment");

        CommentEntity comment = commentRepository.findById(commentId)
                                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다."));

        // TODO: 나중에 인증된 사용자 정보로 변경                        
        User tempUser = userRepository.findById(2L)
                            .orElseThrow(() -> new RuntimeException("임시 사용자를 찾을 수 없습니다."));

        if(!comment.getUser().getId().equals(tempUser.getId())) {
            throw new RuntimeException("댓글 삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }
}
