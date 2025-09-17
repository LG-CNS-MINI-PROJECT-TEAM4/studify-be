package com.lgcns.studify_be.comment.domain.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.studify_be.comment.domain.dto.CommentRequestDTO;
import com.lgcns.studify_be.comment.domain.dto.CommentResponseDTO;
import com.lgcns.studify_be.comment.service.CommentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



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
    
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> update(
        @PathVariable("postId") Integer postId,
        @PathVariable("commentId") Integer commentId, 
        @RequestBody CommentRequestDTO requestDTO
        // @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        System.out.println(">>> CommentCtrl - update");
        System.out.println("commentId: " + commentId);
        System.out.println("requestDTO: " + requestDTO);

        CommentResponseDTO updatedComment = commentService.updateComment(commentId, requestDTO);
        
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}") 
    public ResponseEntity<Void> delete(
        @PathVariable("postId") Integer postId,
        @PathVariable("commentId") Integer commentId
        // @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        System.out.println(">>> CommentCtrl - delete");
        System.out.println("commentId: " + commentId);

        commentService.deleteComment(commentId);

        return ResponseEntity.noContent().build();
    }
}
