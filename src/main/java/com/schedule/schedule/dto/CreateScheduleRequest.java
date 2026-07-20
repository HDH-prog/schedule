package com.schedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 10)
    private String title;

    @NotBlank
    private String content;
}