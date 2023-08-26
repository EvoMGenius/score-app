package com.evo.apatrios.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String faculty;

    @NotBlank
    private String studyGroup;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}