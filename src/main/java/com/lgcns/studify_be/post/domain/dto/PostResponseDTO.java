package com.lgcns.studify_be.post.domain.dto;

import com.lgcns.studify_be.post.domain.entity.Category;
import com.lgcns.studify_be.post.domain.entity.PostEntity;

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
    // private String authorId;

    // authorId, comments 추가 필요
    public static PostResponseDTO fromEntity(PostEntity post) {
        return PostResponseDTO.builder()
                            .postId(post.getPostId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .category(post.getCategory())
                            .build();
    }
}
