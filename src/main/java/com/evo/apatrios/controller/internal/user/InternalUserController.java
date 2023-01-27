package com.evo.apatrios.controller.internal.user;

import com.evo.apatrios.controller.internal.user.dto.UpdateUserDto;
import com.evo.apatrios.controller.user.dto.output.UserDto;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.evo.apatrios.controller.user.mapper.UserMapper.USER_MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/internal/user")
public class InternalUserController {

    private final CustomUserService userService;

    @PutMapping("update/{id}")
    public UserDto update(@RequestBody UpdateUserDto dto, @PathVariable UUID id) {
        UpdateUserArgument argument = USER_MAPPER.toUpdateArgument(dto);

        return USER_MAPPER.toDto(userService.update(argument, id));
    }
}
