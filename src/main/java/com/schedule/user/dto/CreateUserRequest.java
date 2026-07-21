package com.schedule.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @NotBlank
    @Size(min = 2, max = 5)
    private String username;

    @NotBlank
    private String email;

    @NotBlank(message = "비밀번호 필수!")
    @Size(min = 8, message = "비밀번호 8글자 이상!")
    private String password;
}
