package com.evo.apatrios.controller.user;


import com.evo.apatrios.action.buyaward.UserBuyAwardAction;
import com.evo.apatrios.action.buyaward.UserByAwardActionArgument;
import com.evo.apatrios.controller.user.dto.input.SearchUserDto;
import com.evo.apatrios.controller.user.dto.output.UserBuyAwardDto;
import com.evo.apatrios.controller.user.dto.output.UserDto;
import com.evo.apatrios.controller.user.dto.output.UserListDto;
import com.evo.apatrios.security.AuthService;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.argument.SearchUserArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.evo.apatrios.controller.user.mapper.UserMapper.USER_MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class CustomUserController {

    private final CustomUserService userService;

    private final AuthService authService;

    private final UserBuyAwardAction buyAwardAction;

    @GetMapping("list")
    public List<UserListDto> getList(SearchUserDto dto,
                                     @SortDefault(value = {"score"}, direction = Sort.Direction.DESC)
                                     Sort sort) {
        SearchUserArgument argument = USER_MAPPER.toSearchArgument(dto);
        return userService.getList(argument, sort).stream()
                          .map(USER_MAPPER::toListDto)
                          .collect(Collectors.toList());
    }

    @PostMapping("{awardId}/buy")
    public UserBuyAwardDto buyAward(@PathVariable UUID awardId) {
        UUID userId = authService.getAuthorizedUserId();
        UserByAwardActionArgument argument = USER_MAPPER.toBuyAwardActionArgument(userId, awardId);

        return USER_MAPPER.toUserBuyAwardDto(buyAwardAction.buyAward(argument));
    }

    @GetMapping("me")
    public UserDto me() {
        UUID userId = authService.getAuthorizedUserId();
        return USER_MAPPER.toDto(userService.getExisting(userId));
    }
}
