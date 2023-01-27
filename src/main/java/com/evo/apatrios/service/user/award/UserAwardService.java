package com.evo.apatrios.service.user.award;

import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.service.user.award.argument.CreateUserAwardArgument;
import lombok.NonNull;

public interface UserAwardService {
    CustomUserBoughtAward create(@NonNull CreateUserAwardArgument argument);
}
