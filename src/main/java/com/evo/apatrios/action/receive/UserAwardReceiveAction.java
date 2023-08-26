package com.evo.apatrios.action.receive;

import com.evo.apatrios.exception.AwardNotFoundException;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.award.UserAwardService;
import com.evo.apatrios.service.user.award.argument.UpdateUserAwardArgument;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserAwardReceiveAction {

    CustomUserService userService;

    UserAwardService userAwardService;

    public UserAwardReceiveAction(@Qualifier("customUserServiceImpl") CustomUserService userService,
                                  UserAwardService userAwardService) {
        this.userService = userService;
        this.userAwardService = userAwardService;
    }

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
