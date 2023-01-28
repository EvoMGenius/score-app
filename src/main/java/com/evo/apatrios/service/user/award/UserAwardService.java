package com.evo.apatrios.service.user.award;

import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.service.user.award.argument.CreateUserAwardArgument;
import com.evo.apatrios.service.user.award.argument.UpdateUserAwardArgument;
import lombok.NonNull;

import java.util.UUID;

public interface UserAwardService {
    CustomUserBoughtAward create(@NonNull CreateUserAwardArgument argument);

    void update(@NonNull UpdateUserAwardArgument argument, @NonNull UUID id);
}
