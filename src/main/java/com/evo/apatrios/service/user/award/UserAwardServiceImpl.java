package com.evo.apatrios.service.user.award;

import com.evo.apatrios.exception.AwardNotFoundException;
import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.repository.UserAwardRepository;
import com.evo.apatrios.service.user.award.argument.CreateUserAwardArgument;
import com.evo.apatrios.service.user.award.argument.UpdateUserAwardArgument;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAwardServiceImpl implements UserAwardService {

    private final UserAwardRepository repository;

    @Override
    @Transactional
    public CustomUserBoughtAward create(@NonNull CreateUserAwardArgument argument) {
        return repository.save(CustomUserBoughtAward.builder()
                                                    .award(argument.getAward())
                                                    .isReceived(argument.isReceived())
                                                    .build());

    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(@NonNull UpdateUserAwardArgument argument, @NonNull UUID id) {
        CustomUserBoughtAward existing = getExisting(id);

        existing.setReceived(argument.isReceived());
        existing.setAward(argument.getAward());

        repository.save(existing);
    }

    private CustomUserBoughtAward getExisting(UUID id) {
        return repository.findById(id).orElseThrow(() -> new AwardNotFoundException("This UserAward is not found", id));
    }
}
