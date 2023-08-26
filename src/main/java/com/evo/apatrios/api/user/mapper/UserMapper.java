package com.evo.apatrios.api.user.mapper;

import com.evo.apatrios.action.buyaward.UserByAwardActionArgument;
import com.evo.apatrios.api.user.internal.dto.UpdateUserDto;
import com.evo.apatrios.api.user.external.dto.input.SearchUserDto;
import com.evo.apatrios.api.user.external.dto.output.UserBuyAwardDto;
import com.evo.apatrios.api.user.external.dto.output.UserDto;
import com.evo.apatrios.api.user.external.dto.output.UserListDto;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.action.buyaward.UserBuyAward;
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
