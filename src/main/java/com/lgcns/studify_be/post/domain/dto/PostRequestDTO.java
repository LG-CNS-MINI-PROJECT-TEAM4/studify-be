package com.lgcns.studify_be.post.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

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
    private Integer recruitmentCount;
    private List<String> techStack;
    private PostStatus status;
    private LocalDateTime deadline;

    // author 추가 필요
    public PostEntity toEntity() {
        return PostEntity.builder()
                        .title(this.title)
                        .content(this.content)
                        .category(this.category)
                        .recruitmentCount(this.recruitmentCount)
                        .techStack(this.techStack)
                        .status(this.status)
                        .deadline(this.deadline)
                        .build();
    }
}
