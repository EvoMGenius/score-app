package com.evo.apatrios.controller.user.internal;

import com.evo.apatrios.action.receive.UserAwardReceiveAction;
import com.evo.apatrios.controller.user.external.dto.output.UserDto;
import com.evo.apatrios.controller.user.internal.dto.UpdateUserDto;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.evo.apatrios.controller.user.mapper.UserMapper.USER_MAPPER;

@RestController
@RequestMapping("api/v1/internal/user")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InternalUserController {

    CustomUserService userService;

    UserAwardReceiveAction receiveAction;

    public InternalUserController(@Qualifier("customUserServiceImpl") CustomUserService userService,
                                  UserAwardReceiveAction receiveAction) {
        this.userService = userService;
        this.receiveAction = receiveAction;
    }

    @PutMapping("update/{id}")
    public UserDto update(@RequestBody UpdateUserDto dto, @PathVariable UUID id) {
        UpdateUserArgument argument = USER_MAPPER.toUpdateArgument(dto);

        return USER_MAPPER.toDto(userService.update(argument, id));
    }

    @PostMapping("update/score/{userId}/{additionalScore}")
    public UserDto updateScore(@PathVariable UUID userId, @PathVariable Long additionalScore) {
        return USER_MAPPER.toDto(userService.updateScore(userId, additionalScore));
    }

    @PostMapping("{userId}/received/{userAwardId}")
    public void received(@PathVariable UUID userId, @PathVariable UUID userAwardId) {
        receiveAction.receive(userId, userAwardId);
    }
}
