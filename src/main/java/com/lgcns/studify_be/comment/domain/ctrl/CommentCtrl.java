package com.lgcns.studify_be.comment.domain.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.studify_be.comment.domain.dto.CommentRequestDTO;
import com.lgcns.studify_be.comment.domain.dto.CommentResponseDTO;
import com.lgcns.studify_be.comment.service.CommentService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/post/{postId}/comment")
public class CommentCtrl {
    
    @Autowired
    private CommentService commentService;

    @PostMapping("/register")
    public ResponseEntity<List<CommentResponseDTO>> register(
        @PathVariable("postId") Integer postId,
        @RequestBody CommentRequestDTO requestDTO
        // @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        System.out.println(">>> CommentCtrl - register");
        System.out.println("postId: " + postId);
        System.out.println("requestDTO: " + requestDTO);

        List<CommentResponseDTO> comments = commentService.createComment(postId, requestDTO);

        return ResponseEntity.ok(comments);
    }
    
}
