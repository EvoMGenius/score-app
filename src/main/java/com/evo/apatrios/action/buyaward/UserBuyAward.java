package com.evo.apatrios.action.buyaward;

import com.evo.apatrios.model.Award;
import com.evo.apatrios.model.CustomUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBuyAward {

    private CustomUser user;

    private Award award;

    private boolean isAccepted;
}
