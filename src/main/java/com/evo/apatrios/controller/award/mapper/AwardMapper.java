package com.evo.apatrios.controller.award.mapper;

import com.evo.apatrios.controller.award.dto.input.SearchAwardDto;
import com.evo.apatrios.controller.award.dto.output.AwardDto;
import com.evo.apatrios.controller.internal.award.dto.CreateAwardDto;
import com.evo.apatrios.controller.internal.award.dto.UpdateAwardDto;
import com.evo.apatrios.model.Award;
import com.evo.apatrios.service.award.argument.CreateAwardArgument;
import com.evo.apatrios.service.award.argument.SearchAwardArgument;
import com.evo.apatrios.service.award.argument.UpdateAwardArgument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AwardMapper {

    AwardMapper AWARD_MAPPER = Mappers.getMapper(AwardMapper.class);

    SearchAwardArgument toSearchArgument(SearchAwardDto dto);

    AwardDto toDto(Award award);

    CreateAwardArgument toCreateArgument(CreateAwardDto dto);

    UpdateAwardArgument toUpdateArgument(UpdateAwardDto dto);
}
