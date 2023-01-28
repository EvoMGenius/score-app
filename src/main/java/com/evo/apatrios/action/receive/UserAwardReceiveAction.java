package com.evo.apatrios.action.receive;

import com.evo.apatrios.exception.AwardNotFoundException;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.award.UserAwardService;
import com.evo.apatrios.service.user.award.argument.UpdateUserAwardArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserAwardReceiveAction {

    private final CustomUserService userService;

    private final UserAwardService userAwardService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void receive(UUID userId, UUID userAwardId) {
        CustomUser user = userService.getExisting(userId);

        CustomUserBoughtAward award = user.getAwards().stream()
                                          .filter(customUserBoughtAward ->
                                                          customUserBoughtAward.getId().equals(userAwardId))
                                          .findFirst()
                                          .orElseThrow(() -> new AwardNotFoundException("User Award is not found", userAwardId));
        award.setReceived(true);
        userAwardService.update(UpdateUserAwardArgument.builder()
                                                       .award(award.getAward())
                                                       .isReceived(award.isReceived())
                                                       .build(), award.getId());
    }
}
