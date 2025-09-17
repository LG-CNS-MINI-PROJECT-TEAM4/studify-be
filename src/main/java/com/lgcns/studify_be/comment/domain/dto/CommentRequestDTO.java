package com.lgcns.studify_be.comment.domain.dto;

import com.lgcns.studify_be.comment.domain.entity.CommentEntity;
import com.lgcns.studify_be.post.domain.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentRequestDTO {
    private String content;
    private Long postId;

    public CommentEntity toEntity(PostEntity post) {
        return CommentEntity.builder()
                            .content(this.content)
                            .post(post)
                            .build();
    }
}