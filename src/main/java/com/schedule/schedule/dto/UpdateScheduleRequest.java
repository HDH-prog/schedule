package com.schedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
