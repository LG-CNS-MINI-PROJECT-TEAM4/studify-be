package com.lgcns.studify_be.post.ctrl;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.studify_be.post.domain.dto.PostRequestDTO;
import com.lgcns.studify_be.post.domain.dto.PostResponseDTO;
import com.lgcns.studify_be.post.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("studify/api/v1/post")
public class PostCtrl {

    @Autowired
    private PostService postService;
    
    // 모집글 생성
    @PostMapping("/posts")
    public ResponseEntity<?> register(@RequestBody PostRequestDTO request) {
        PostResponseDTO response = postService.register(request);
        
        if( response != null ) {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
