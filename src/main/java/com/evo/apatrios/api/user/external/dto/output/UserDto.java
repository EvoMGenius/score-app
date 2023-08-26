package com.evo.apatrios.api.user.external.dto.output;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

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

    private List<UserBoughtAwardDto> awards;
}
