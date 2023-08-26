package com.evo.apatrios.api.user.internal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String faculty;

    @NotBlank
    private String studyGroup;

    @NotBlank
    private String email;

    @NotNull
    private Long score;
}
