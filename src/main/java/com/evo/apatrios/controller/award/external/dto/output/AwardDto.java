package com.evo.apatrios.controller.award.external.dto.output;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class AwardDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private Long cost;

}
