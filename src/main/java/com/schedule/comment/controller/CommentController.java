package com.schedule.comment.controller;

import com.schedule.comment.dto.CreateCommentRequest;
import com.schedule.comment.dto.CreateCommentResponse;
import com.schedule.comment.dto.GetCommentsResponse;
import com.schedule.comment.entity.Comment;
import com.schedule.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 저장
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> saveComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.save(scheduleId, request));
    }
    // 전체 조회
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetCommentsResponse>> getComments(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAll(scheduleId));
    }
}
