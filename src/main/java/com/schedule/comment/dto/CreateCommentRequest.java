package com.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotNull
    @NotBlank
    private Long userId;

    @Size(min = 1, max = 100)
    private String content;
}
