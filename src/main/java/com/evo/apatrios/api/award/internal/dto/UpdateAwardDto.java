package com.evo.apatrios.api.award.internal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateAwardDto {

    @NotBlank
    private String name;

    @NotNull
    private Long cost;

}
