package com.evo.apatrios.controller.user.dto.output;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;

    private String fullName;

    private String faculty;

    private String studyGroup;

    private String email;

    private Long score;

    private List<UserBoughtAwardDto> awards;
}
