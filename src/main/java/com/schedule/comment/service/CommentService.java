package com.schedule.comment.service;

import com.schedule.comment.dto.CreateCommentRequest;
import com.schedule.comment.dto.CreateCommentResponse;
import com.schedule.comment.dto.GetCommentsResponse;
import com.schedule.comment.entity.Comment;
import com.schedule.comment.repository.CommentRepository;
import com.schedule.common.exception.ScheduleNotFoundException;
import com.schedule.common.exception.UserNotFoundException;
import com.schedule.schedule.entity.Schedule;
import com.schedule.schedule.repository.ScheduleRepository;
import com.schedule.user.entity.User;
import com.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 저장 -> CreateCommentRequest, CreateCommentResponse
    @Transactional
    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );
        Comment comment = new Comment(schedule, user, request.getContent());
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getSchedule().getId(),
                savedComment.getUser().getId(),
                savedComment.getUser().getUsername(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentsResponse> getAll(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ScheduleNotFoundException("없는 일정입니다.");
        }
        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);
        return comments.stream()
                .map(comment -> new GetCommentsResponse(
                        comment.getId(),
                        comment.getSchedule().getId(),
                        comment.getUser().getId(),
                        comment.getUser().getUsername(),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                ))
                .toList();
    }
}
