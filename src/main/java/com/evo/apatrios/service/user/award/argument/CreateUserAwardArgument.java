package com.evo.apatrios.service.user.award.argument;

import com.evo.apatrios.model.Award;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserAwardArgument {

    Award award;

    boolean isReceived;
}
