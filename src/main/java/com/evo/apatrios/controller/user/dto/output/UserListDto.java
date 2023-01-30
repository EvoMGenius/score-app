package com.evo.apatrios.controller.user.dto.output;

import lombok.Data;

import java.util.UUID;

@Data
public class UserListDto {

    private UUID id;

    private String fullName;

    private String faculty;

    private String studyGroup;

    private String email;

    private Long score;
}
