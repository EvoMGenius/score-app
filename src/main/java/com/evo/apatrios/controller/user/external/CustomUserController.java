package com.evo.apatrios.controller.user.external;


import com.antkorwin.ioutils.multipartfile.FileResponse;
import com.evo.apatrios.action.buyaward.UserBuyAwardAction;
import com.evo.apatrios.action.buyaward.UserByAwardActionArgument;
import com.evo.apatrios.controller.user.external.dto.input.SearchUserDto;
import com.evo.apatrios.controller.user.external.dto.output.UserBuyAwardDto;
import com.evo.apatrios.controller.user.external.dto.output.UserDto;
import com.evo.apatrios.controller.user.external.dto.output.UserListDto;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.argument.SearchUserArgument;
import com.evo.apatrios.service.utils.FileQrCodeGenerator;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import ru.themikhailz.service.AuthService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.evo.apatrios.controller.user.mapper.UserMapper.USER_MAPPER;

@RestController
@RequestMapping("api/v1/users")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomUserController {

    CustomUserService userService;

    AuthService authService;

    UserBuyAwardAction buyAwardAction;

    public CustomUserController(@Qualifier("customUserServiceImpl") CustomUserService userService,
                                AuthService authService,
                                UserBuyAwardAction buyAwardAction) {
        this.userService = userService;
        this.authService = authService;
        this.buyAwardAction = buyAwardAction;
    }

    @GetMapping("list")
    public List<UserListDto> getList(SearchUserDto dto,
                                     @SortDefault(value = {"score"}, direction = Sort.Direction.ASC)
                                     Sort sort) {
        SearchUserArgument argument = USER_MAPPER.toSearchArgument(dto);
        return userService.getList(argument, sort).stream()
                          .map(USER_MAPPER::toListDto)
                          .collect(Collectors.toList());
    }

    @PostMapping("{awardId}/buy")
    public UserBuyAwardDto buyAward(@PathVariable UUID awardId) throws Exception {
        UUID userId = authService.getAuthorizedUserId();
        UserByAwardActionArgument argument = USER_MAPPER.toBuyAwardActionArgument(userId, awardId);

        return USER_MAPPER.toUserBuyAwardDto(buyAwardAction.buyAward(argument));
    }

    @GetMapping("{id}/user")
    public UserDto getById(@PathVariable UUID id) {
        return USER_MAPPER.toDto(userService.getExisting(id));
    }

    @SneakyThrows
    @GetMapping("my/qr-code")
    public void getMyQrCode(HttpServletResponse httpServletResponse) {
        UUID authorizedUserId = authService.getAuthorizedUserId();
        File qr = FileQrCodeGenerator.generate(authorizedUserId.toString(), 500);
        FileResponse.builder()
                    .file(qr)
                    .filename("qrrr.jpg")
                    .mimeType("image/jpeg")
                    .response(httpServletResponse)
                    .build();
    }

    @GetMapping("me")
    public UserDto me() throws Exception {
        UUID userId = authService.getAuthorizedUserId();
        return USER_MAPPER.toDto(userService.getExisting(userId));
    }
}
