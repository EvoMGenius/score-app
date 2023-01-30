package com.evo.apatrios.controller.user.dto.output;

import com.evo.apatrios.controller.award.dto.output.AwardDto;
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
