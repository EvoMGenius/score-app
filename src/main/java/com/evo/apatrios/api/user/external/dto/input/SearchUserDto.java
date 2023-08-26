package com.evo.apatrios.api.user.external.dto.input;

import lombok.Data;

@Data
public class SearchUserDto {
    private String fullName;

    private String faculty;

    private String studyGroup;

    private String email;
}
