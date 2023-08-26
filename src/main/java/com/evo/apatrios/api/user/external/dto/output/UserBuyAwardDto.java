package com.evo.apatrios.api.user.external.dto.output;

import com.evo.apatrios.api.award.external.dto.output.AwardDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class UserBuyAwardDto {

    @Valid
    @NotNull
    private UserListDto user;

    @Valid
    @NotNull
    private AwardDto award;

    @NotNull
    private boolean isAccepted;
}
