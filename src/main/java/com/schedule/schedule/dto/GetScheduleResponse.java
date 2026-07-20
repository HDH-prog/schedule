package com.schedule.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {
    private final Long id;
    private final String username;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetScheduleResponse(Long id, String username, String title,
                                LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}