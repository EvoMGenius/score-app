package com.evo.apatrios.action.buyaward;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UserByAwardActionArgument {

    UUID userId;

    UUID awardId;
}
