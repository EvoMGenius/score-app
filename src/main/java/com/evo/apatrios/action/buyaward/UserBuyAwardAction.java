package com.evo.apatrios.action.buyaward;

import com.evo.apatrios.model.Award;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.UserBuyAward;
import com.evo.apatrios.service.award.AwardService;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserBuyAwardAction {

    private final CustomUserService userService;

    private final AwardService awardService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserBuyAward buyAward(UserByAwardActionArgument argument) {

        CustomUser user = userService.getExisting(argument.getUserId());
        Award award = awardService.getExisting(argument.getAwardId());

        if (user.getScore() < award.getCost()) {
            return UserBuyAward.builder()
                               .user(user)
                               .award(award)
                               .isAccepted(false)
                               .build();
        }

        user.setScore(user.getScore() - award.getCost());
        user.getAwards().add(award);

        user = userService.update(UpdateUserArgument.builder()
                                                    .fullName(user.getFullName())
                                                    .faculty(user.getFaculty())
                                                    .studyGroup(user.getStudyGroup())
                                                    .email(user.getEmail())
                                                    .password(user.getPassword())
                                                    .role(user.getRole())
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
