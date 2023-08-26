package com.evo.apatrios.api.user.external.dto.output;

import com.evo.apatrios.api.award.external.dto.output.AwardDto;
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
