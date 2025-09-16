package com.lgcns.studify_be.post.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.studify_be.post.domain.dto.PostRequestDTO;
import com.lgcns.studify_be.post.domain.dto.PostResponseDTO;
import com.lgcns.studify_be.post.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("studify/api/v1/post")
public class PostCtrl {

    @Autowired
    private PostService postService;
    
    // 모집글 생성
    @Operation(summary = "모집글 생성")
    @PostMapping("/posts")
    public ResponseEntity<?> register(@RequestBody PostRequestDTO request) {
        PostResponseDTO response = postService.register(request);
        
        if( response != null ) {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 모집글 전체 조회
    @Operation(summary = "모집글 전체 조회")
    @GetMapping("/posts")
    public ResponseEntity<?> readPostList() {
        List<PostResponseDTO> postList = postService.readPostList();
        return new ResponseEntity<List<PostResponseDTO>>(postList, HttpStatus.OK) ; 
    }

    // 모집글 상세 조회
    @Operation(summary = "모집글 상세 조회")
    @GetMapping("/detail/{postId}")
    public ResponseEntity<?> readPostDetail(@PathVariable("postId") Integer postId) {
        PostResponseDTO response = postService.readPostDetail(postId);
        if( response != null ) {
            return new ResponseEntity<>(response , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
    }

    // 특정 모집글 검색( 제목 | 본문 | 제목 + 본문)
    @Operation(summary = "키워드로 모집글 검색")
    @GetMapping("/search")
    public ResponseEntity<?> findPostByTitleContent(
            @Parameter(description = "검색 키워드")
            @RequestParam String keyword,
            @Parameter(description = "검색 타입 (title=제목, content=본문, all=제목+본문)", example = "all")
            @RequestParam(defaultValue = "all") String type) {
        List<PostResponseDTO> postList;
        
        switch (type) {
            case "title":
                postList = postService.findPostByTitle(keyword);
                break;
            case "content":
                postList = postService.findPostByContent(keyword);
                break;
            default:
                postList = postService.findPostByTitleContent(keyword);
        }
        return new ResponseEntity<List<PostResponseDTO>>(postList, HttpStatus.OK) ; 
    }  
    
}
