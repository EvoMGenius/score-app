package com.evo.apatrios.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBuyAward {

    private CustomUser user;

    private Award award;

    private boolean isAccepted;
}
