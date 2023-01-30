package com.evo.apatrios.controller.user.dto.output;

import com.evo.apatrios.controller.award.dto.output.AwardDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserBoughtAwardDto {

    @NotNull
    private UUID id;

    @Valid
    @NotNull
    private AwardDto award;

    @NotNull
    private boolean isReceived;
}
