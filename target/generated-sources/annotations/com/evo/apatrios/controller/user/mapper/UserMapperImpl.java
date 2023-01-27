package com.evo.apatrios.controller.user.mapper;

import com.evo.apatrios.action.buyaward.UserBuyAward;
import com.evo.apatrios.action.buyaward.UserByAwardActionArgument;
import com.evo.apatrios.controller.award.dto.output.AwardDto;
import com.evo.apatrios.controller.internal.user.dto.UpdateUserDto;
import com.evo.apatrios.controller.user.dto.input.SearchUserDto;
import com.evo.apatrios.controller.user.dto.output.UserBuyAwardDto;
import com.evo.apatrios.controller.user.dto.output.UserDto;
import com.evo.apatrios.controller.user.dto.output.UserListDto;
import com.evo.apatrios.model.Award;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.service.user.argument.SearchUserArgument;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-27T17:54:51+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_345 (Temurin)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public SearchUserArgument toSearchArgument(SearchUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        SearchUserArgument.SearchUserArgumentBuilder searchUserArgument = SearchUserArgument.builder();

        searchUserArgument.fullName( dto.getFullName() );
        searchUserArgument.faculty( dto.getFaculty() );
        searchUserArgument.studyGroup( dto.getStudyGroup() );
        searchUserArgument.email( dto.getEmail() );

        return searchUserArgument.build();
    }

    @Override
    public UserListDto toListDto(CustomUser customUser) {
        if ( customUser == null ) {
            return null;
        }

        UserListDto userListDto = new UserListDto();

        userListDto.setFullName( customUser.getFullName() );
        userListDto.setFaculty( customUser.getFaculty() );
        userListDto.setStudyGroup( customUser.getStudyGroup() );
        userListDto.setEmail( customUser.getEmail() );
        userListDto.setScore( customUser.getScore() );

        return userListDto;
    }

    @Override
    public UserByAwardActionArgument toBuyAwardActionArgument(UUID userId, UUID awardId) {
        if ( userId == null && awardId == null ) {
            return null;
        }

        UserByAwardActionArgument.UserByAwardActionArgumentBuilder userByAwardActionArgument = UserByAwardActionArgument.builder();

        userByAwardActionArgument.userId( userId );
        userByAwardActionArgument.awardId( awardId );

        return userByAwardActionArgument.build();
    }

    @Override
    public UserBuyAwardDto toUserBuyAwardDto(UserBuyAward userBuyAward) {
        if ( userBuyAward == null ) {
            return null;
        }

        UserBuyAwardDto userBuyAwardDto = new UserBuyAwardDto();

        userBuyAwardDto.setUser( toListDto( userBuyAward.getUser() ) );
        userBuyAwardDto.setAward( awardToAwardDto( userBuyAward.getAward() ) );
        userBuyAwardDto.setAccepted( userBuyAward.isAccepted() );

        return userBuyAwardDto;
    }

    @Override
    public UserDto toDto(CustomUser user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setFullName( user.getFullName() );
        userDto.setFaculty( user.getFaculty() );
        userDto.setStudyGroup( user.getStudyGroup() );
        userDto.setEmail( user.getEmail() );
        userDto.setScore( user.getScore() );
        userDto.setAwards( customUserBoughtAwardListToAwardDtoList( user.getAwards() ) );

        return userDto;
    }

    @Override
    public UpdateUserArgument toUpdateArgument(UpdateUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UpdateUserArgument.UpdateUserArgumentBuilder updateUserArgument = UpdateUserArgument.builder();

        updateUserArgument.fullName( dto.getFullName() );
        updateUserArgument.faculty( dto.getFaculty() );
        updateUserArgument.studyGroup( dto.getStudyGroup() );
        updateUserArgument.email( dto.getEmail() );
        updateUserArgument.score( dto.getScore() );

        return updateUserArgument.build();
    }

    protected AwardDto awardToAwardDto(Award award) {
        if ( award == null ) {
            return null;
        }

        AwardDto awardDto = new AwardDto();

        awardDto.setName( award.getName() );
        awardDto.setCost( award.getCost() );

        return awardDto;
    }

    protected AwardDto customUserBoughtAwardToAwardDto(CustomUserBoughtAward customUserBoughtAward) {
        if ( customUserBoughtAward == null ) {
            return null;
        }

        AwardDto awardDto = new AwardDto();

        return awardDto;
    }

    protected List<AwardDto> customUserBoughtAwardListToAwardDtoList(List<CustomUserBoughtAward> list) {
        if ( list == null ) {
            return null;
        }

        List<AwardDto> list1 = new ArrayList<AwardDto>( list.size() );
        for ( CustomUserBoughtAward customUserBoughtAward : list ) {
            list1.add( customUserBoughtAwardToAwardDto( customUserBoughtAward ) );
        }

        return list1;
    }
}
