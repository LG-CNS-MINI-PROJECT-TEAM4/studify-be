package com.lgcns.studify_be.comment.domain.dto;

import com.lgcns.studify_be.comment.domain.entity.CommentEntity;
import com.lgcns.studify_be.post.domain.entity.PostEntity;

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