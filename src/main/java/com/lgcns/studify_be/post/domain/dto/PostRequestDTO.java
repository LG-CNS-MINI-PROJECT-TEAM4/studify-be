package com.lgcns.studify_be.post.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.lgcns.studify_be.post.domain.entity.Category;
import com.lgcns.studify_be.post.domain.entity.Position;
import com.lgcns.studify_be.post.domain.entity.PostEntity;
import com.lgcns.studify_be.post.domain.entity.PostStatus;
import com.lgcns.studify_be.user.User;

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
    private String category;
    private Integer recruitmentCount;
    private List<String> techStack;
    private String status;
    private LocalDateTime deadline;
    private String meetingType;
    private String duration;
    private List<String> position;
    private Long authorId;

    // author 추가 필요
    public PostEntity toEntity(User author) {
        return PostEntity.builder()
                        .author(author)
                        .title(this.title)
                        .content(this.content)
                        .category(Category.from(this.category))
                        .recruitmentCount(this.recruitmentCount)
                        .techStack(this.techStack)
                        .status(PostStatus.from(this.status))
                        .deadline(this.deadline)
                        .meetingType(this.meetingType)
                        .duration(this.duration)
                        .position(Position.fromList(this.position))
                        .build();
    }
}
