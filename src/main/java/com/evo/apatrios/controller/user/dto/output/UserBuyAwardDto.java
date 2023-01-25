package com.evo.apatrios.controller.user.dto.output;

import com.evo.apatrios.controller.award.dto.output.AwardDto;
import lombok.Data;

@Data
public class UserBuyAwardDto {

    private UserListDto user;

    private AwardDto award;

    private boolean isAccepted;
}
