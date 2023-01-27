package com.evo.apatrios.controller.award.mapper;

import com.evo.apatrios.controller.award.dto.input.SearchAwardDto;
import com.evo.apatrios.controller.award.dto.output.AwardDto;
import com.evo.apatrios.controller.internal.award.dto.CreateAwardDto;
import com.evo.apatrios.controller.internal.award.dto.UpdateAwardDto;
import com.evo.apatrios.model.Award;
import com.evo.apatrios.service.award.argument.CreateAwardArgument;
import com.evo.apatrios.service.award.argument.SearchAwardArgument;
import com.evo.apatrios.service.award.argument.UpdateAwardArgument;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-27T17:54:51+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_345 (Temurin)"
)
public class AwardMapperImpl implements AwardMapper {

    @Override
    public SearchAwardArgument toSearchArgument(SearchAwardDto dto) {
        if ( dto == null ) {
            return null;
        }

        SearchAwardArgument.SearchAwardArgumentBuilder searchAwardArgument = SearchAwardArgument.builder();

        searchAwardArgument.name( dto.getName() );
        searchAwardArgument.cost( dto.getCost() );

        return searchAwardArgument.build();
    }

    @Override
    public AwardDto toDto(Award award) {
        if ( award == null ) {
            return null;
        }

        AwardDto awardDto = new AwardDto();

        awardDto.setName( award.getName() );
        awardDto.setCost( award.getCost() );

        return awardDto;
    }

    @Override
    public CreateAwardArgument toCreateArgument(CreateAwardDto dto) {
        if ( dto == null ) {
            return null;
        }

        CreateAwardArgument.CreateAwardArgumentBuilder createAwardArgument = CreateAwardArgument.builder();

        createAwardArgument.name( dto.getName() );
        createAwardArgument.cost( dto.getCost() );

        return createAwardArgument.build();
    }

    @Override
    public UpdateAwardArgument toUpdateArgument(UpdateAwardDto dto) {
        if ( dto == null ) {
            return null;
        }

        UpdateAwardArgument.UpdateAwardArgumentBuilder updateAwardArgument = UpdateAwardArgument.builder();

        updateAwardArgument.name( dto.getName() );
        updateAwardArgument.cost( dto.getCost() );

        return updateAwardArgument.build();
    }
}
