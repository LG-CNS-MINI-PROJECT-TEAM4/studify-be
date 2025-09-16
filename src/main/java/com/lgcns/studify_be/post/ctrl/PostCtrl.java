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
    @GetMapping("/detail")
    public ResponseEntity<?> readPostDetail(Integer postId) {
        PostResponseDTO response = postService.readPostDetail(postId);
        if( response != null ) {
            return new ResponseEntity<>(response , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
    }

    // 특정 모집글 검색(제목)
    @Operation(summary = "제목 키워드로 모집글 검색")
    @GetMapping("/title/{keyword}")
    public ResponseEntity<?> findPostByTitle(@PathVariable("keyword") String keyword) {
        List<PostResponseDTO> postList = postService.findPostByTitle(keyword);
        return new ResponseEntity<List<PostResponseDTO>>(postList, HttpStatus.OK) ; 
    }

    // 특정 모집글 검색(본문)
    @Operation(summary = "본문 키워드로 모집글 검색")
    @GetMapping("/content/{keyword}")
    public ResponseEntity<?> findPostByContent(@PathVariable("keyword") String keyword) {
        List<PostResponseDTO> postList = postService.findPostByContent(keyword);
        return new ResponseEntity<List<PostResponseDTO>>(postList, HttpStatus.OK) ; 
    }

    // 특정 모집글 검색(제목 + 본문)
    @Operation(summary = "제목과 본문 키워드로 모집글 검색")
    @GetMapping("/titlecontent/{keyword}")
    public ResponseEntity<?> findPostByTitleContent(@PathVariable("keyword") String keyword) {
        List<PostResponseDTO> postList = postService.findPostByTitleContent(keyword);
        return new ResponseEntity<List<PostResponseDTO>>(postList, HttpStatus.OK) ; 
    }  
    
}
