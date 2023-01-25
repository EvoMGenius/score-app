package com.evo.apatrios.controller.internal.user.dto;

import lombok.Data;

@Data
public class UpdateUserDto {

    private String fullName;

    private String faculty;

    private String studyGroup;

    private String email;

    private Long score;
}
