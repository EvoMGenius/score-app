package com.evo.apatrios.controller.user.dto.output;

import com.evo.apatrios.controller.award.dto.output.AwardDto;
import lombok.Data;

import java.util.UUID;

@Data
public class UserBoughtAwardDto {

    private UUID id;

    private AwardDto award;

    private boolean isReceived;
}
