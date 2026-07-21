package com.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 100)
    private String content;
}
