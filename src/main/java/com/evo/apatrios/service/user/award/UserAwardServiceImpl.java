package com.evo.apatrios.service.user.award;

import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.repository.UserAwardRepository;
import com.evo.apatrios.service.user.award.argument.CreateUserAwardArgument;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAwardServiceImpl implements UserAwardService {

    private final UserAwardRepository repository;

    @Override
    public CustomUserBoughtAward create(@NonNull CreateUserAwardArgument argument) {
        return repository.save(CustomUserBoughtAward.builder()
                                                    .award(argument.getAward())
                                                    .isReceived(argument.isReceived())
                                                    .build());

    }
}
