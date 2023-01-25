package com.evo.apatrios.controller.user.mapper;

import com.evo.apatrios.action.buyaward.UserByAwardActionArgument;
import com.evo.apatrios.controller.internal.user.dto.UpdateUserDto;
import com.evo.apatrios.controller.user.dto.input.SearchUserDto;
import com.evo.apatrios.controller.user.dto.output.UserBuyAwardDto;
import com.evo.apatrios.controller.user.dto.output.UserDto;
import com.evo.apatrios.controller.user.dto.output.UserListDto;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.UserBuyAward;
import com.evo.apatrios.service.user.argument.SearchUserArgument;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    SearchUserArgument toSearchArgument(SearchUserDto dto);

    UserListDto toListDto(CustomUser customUser);

    UserByAwardActionArgument toBuyAwardActionArgument(UUID userId, UUID awardId);

    UserBuyAwardDto toUserBuyAwardDto(UserBuyAward userBuyAward);

    UserDto toDto(CustomUser user);

    UpdateUserArgument toUpdateArgument(UpdateUserDto dto);
}
