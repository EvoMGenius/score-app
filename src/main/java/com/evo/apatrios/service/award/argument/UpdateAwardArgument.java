package com.evo.apatrios.service.award.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateAwardArgument {

    String name;

    Long cost;
}
