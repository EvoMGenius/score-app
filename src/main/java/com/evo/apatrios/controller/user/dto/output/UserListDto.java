package com.evo.apatrios.controller.user.dto.output;

import lombok.Data;

@Data
public class UserListDto {

    private String fullName;

    private String faculty;

    private String studyGroup;

    private String email;

    private Long score;
}
