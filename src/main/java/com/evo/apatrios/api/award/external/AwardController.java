package com.evo.apatrios.api.award.external;

import com.evo.apatrios.api.award.external.dto.input.SearchAwardDto;
import com.evo.apatrios.api.award.external.dto.output.AwardDto;
import com.evo.apatrios.service.award.AwardService;
import com.evo.apatrios.service.award.argument.SearchAwardArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.evo.apatrios.api.award.mapper.AwardMapper.AWARD_MAPPER;

@RequestMapping("api/v1/award")
@RestController
@RequiredArgsConstructor
public class AwardController {

    private final AwardService awardService;

    @GetMapping("list")
    public List<AwardDto> getList(SearchAwardDto dto,
                                  @SortDefault(value = {"cost"}, direction = Sort.Direction.DESC)
                                  Sort sort) {
        SearchAwardArgument argument = AWARD_MAPPER.toSearchArgument(dto);
        return awardService.getList(argument, sort).stream()
                           .map(AWARD_MAPPER::toDto)
                           .collect(Collectors.toList());
    }
}
