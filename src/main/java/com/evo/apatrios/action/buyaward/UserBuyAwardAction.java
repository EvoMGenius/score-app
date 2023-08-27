package com.evo.apatrios.action.buyaward;

import com.evo.apatrios.model.Award;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.service.award.AwardService;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import com.evo.apatrios.service.user.award.UserAwardService;
import com.evo.apatrios.service.user.award.argument.CreateUserAwardArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserBuyAwardAction {

    CustomUserService userService;

    AwardService awardService;

    UserAwardService userAwardService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserBuyAward execute(UserByAwardActionArgument argument) {

        CustomUser user = userService.getExisting(argument.getUserId());
        Award award = awardService.getExisting(argument.getAwardId());

        if (user.getScore() < award.getCost()) {
            return UserBuyAward.builder()
                               .user(user)
                               .award(award)
                               .isAccepted(false)
                               .build();
        }

        CustomUserBoughtAward customUserBoughtAward = userAwardService.create(CreateUserAwardArgument.builder()
                                                                                                     .award(award)
                                                                                                     .isReceived(false)
                                                                                                     .build());

        user.setScore(user.getScore() - award.getCost());
        user.getAwards().add(customUserBoughtAward);

        user = userService.update(UpdateUserArgument.builder()
                                                    .fullName(user.getFullName())
                                                    .faculty(user.getFaculty())
                                                    .studyGroup(user.getStudyGroup())
                                                    .email(user.getEmail())
                                                    .awards(user.getAwards())
                                                    .score(user.getScore())
                                                    .build(), user.getId());
        return UserBuyAward.builder()
                           .user(user)
                           .award(award)
                           .isAccepted(true)
                           .build();
    }
}
