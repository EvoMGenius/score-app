package com.evo.apatrios.controller.award.dto.output;

import lombok.Data;

import java.util.UUID;

@Data
public class AwardDto {

    private UUID id;

    private String name;

    private Long cost;

}
