package com.evo.apatrios.controller.user.external.dto.output;

import com.evo.apatrios.controller.award.external.dto.output.AwardDto;
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
