package com.lgcns.studify_be.comment.domain.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.studify_be.comment.domain.dto.CommentRequestDTO;
import com.lgcns.studify_be.comment.domain.dto.CommentResponseDTO;
import com.lgcns.studify_be.comment.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Operation(summary = "댓글 등록")
    public ResponseEntity<List<CommentResponseDTO>> register(
        @PathVariable("postId") Long postId,
        @RequestBody CommentRequestDTO requestDTO,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        System.out.println(">>> CommentCtrl - register");
        System.out.println("postId: " + postId);
        System.out.println("requestDTO: " + requestDTO);

        List<CommentResponseDTO> comments = commentService.createComment(postId, requestDTO, userDetails.getUsername());

        return ResponseEntity.ok(comments);
    }
    
    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정")
    public ResponseEntity<CommentResponseDTO> update(
        @PathVariable("postId") Long postId,
        @PathVariable("commentId") Long commentId,
        @RequestBody CommentRequestDTO requestDTO,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        System.out.println(">>> CommentCtrl - update");
        System.out.println("commentId: " + commentId);
        System.out.println("requestDTO: " + requestDTO);

        CommentResponseDTO updatedComment = commentService.updateComment(commentId, requestDTO, userDetails.getUsername());
        
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<Void> delete(
        @PathVariable("postId") Long postId,
        @PathVariable("commentId") Long commentId,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        System.out.println(">>> CommentCtrl - delete");
        System.out.println("commentId: " + commentId);

        commentService.deleteComment(commentId, userDetails.getUsername());

        return ResponseEntity.noContent().build();
    }
}
