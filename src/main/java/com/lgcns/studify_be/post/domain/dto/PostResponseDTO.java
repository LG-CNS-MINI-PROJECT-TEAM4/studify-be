package com.lgcns.studify_be.post.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.lgcns.studify_be.comment.domain.dto.CommentResponseDTO;
import com.lgcns.studify_be.post.domain.entity.Category;
import com.lgcns.studify_be.post.domain.entity.PostEntity;
import com.lgcns.studify_be.post.domain.entity.PostStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponseDTO {
    
    private Integer postId;
    private String title;
    private String content;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer recruitmentCount;
    private List<String> techStack;
    private LocalDateTime deadline;
    private PostStatus status;
    private List<CommentResponseDTO> comments;
    // private String authorId;

    // authorId 추가 필요
    public static PostResponseDTO fromEntity(PostEntity post) {
        List<CommentResponseDTO> commentDTOs = post.getComments().stream()
                                                    .map(CommentResponseDTO::fromEntity)
                                                    .toList();

        return PostResponseDTO.builder()
                            .postId(post.getPostId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .category(post.getCategory())
                            .createdAt(post.getCreatedAt())
                            .updatedAt(post.getUpdatedAt())
                            .recruitmentCount(post.getRecruitmentCount())
                            .techStack(post.getTechStack())
                            .deadline(post.getDeadline())
                            .status(post.getStatus())
                            .comments(commentDTOs)
                            .build();
    }
}
