package com.evo.apatrios.service.award.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchAwardArgument {

    String name;

    Long cost;
}
