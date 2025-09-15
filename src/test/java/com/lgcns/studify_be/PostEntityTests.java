package com.lgcns.studify_be;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.lgcns.studify_be.post.domain.dto.PostRequestDTO;
import com.lgcns.studify_be.post.domain.entity.Category;
import com.lgcns.studify_be.post.domain.entity.PostEntity;
import com.lgcns.studify_be.post.repository.PostRepository;

@SpringBootTest
public class PostEntityTests {
    
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    @Commit
    public void createPost() {
        PostRequestDTO request = PostRequestDTO.builder()
                                                .title("test-title1")
                                                .content("test-content1")
                                                .category(Category.STUDY)
                                                .build();

        PostEntity savedPost = postRepository.save(request.toEntity());
        System.out.println(">>> 저장 완료 : " + savedPost);

        PostEntity foundPost = postRepository.findById(savedPost.getPostId())
                        .orElseThrow(() -> new RuntimeException("Post not exists"));

        System.out.println(">>> 조회 결과 : " + foundPost);

        List<PostEntity> postList = postRepository.findAll();
        System.out.println(">>> 포스트 수 : " + postList.size());
        postList.forEach(System.out::println);
    }

}
