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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequestDTO {
    
    private String title;
    private String content;
    private Category category;
    // private String authorId;

    // author 추가 필요
    public PostEntity toEntity() {
        return PostEntity.builder()
                        .title(this.title)
                        .content(this.content)
                        .category(this.category)
                        .build();
    }
}
