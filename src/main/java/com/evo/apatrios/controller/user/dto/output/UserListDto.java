package com.evo.apatrios.controller.user.dto.output;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserListDto {

    @NotNull
    private UUID id;

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
