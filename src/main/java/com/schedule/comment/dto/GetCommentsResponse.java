package com.schedule.comment.dto;

import com.schedule.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentsResponse {
    private final Long id;
    private final Long scheduleId;
    private final Long userId;
    private final String username;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentsResponse(Long id, Long scheduleId, Long userId, String username, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
