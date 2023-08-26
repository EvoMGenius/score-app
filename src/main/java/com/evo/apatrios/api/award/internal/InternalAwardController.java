package com.evo.apatrios.api.award.internal;

import com.evo.apatrios.api.award.external.dto.output.AwardDto;
import com.evo.apatrios.api.award.internal.dto.CreateAwardDto;
import com.evo.apatrios.api.award.internal.dto.UpdateAwardDto;
import com.evo.apatrios.service.award.AwardService;
import com.evo.apatrios.service.award.argument.CreateAwardArgument;
import com.evo.apatrios.service.award.argument.UpdateAwardArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.evo.apatrios.api.award.mapper.AwardMapper.AWARD_MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/internal/award")
public class InternalAwardController {

    private final AwardService awardService;

    @PostMapping("create")
    public AwardDto create(@RequestBody CreateAwardDto dto) {
        CreateAwardArgument argument = AWARD_MAPPER.toCreateArgument(dto);

        return AWARD_MAPPER.toDto(awardService.create(argument));
    }

    @PutMapping("update/{id}")
    public AwardDto update(@RequestBody UpdateAwardDto dto, @PathVariable UUID id) {
        UpdateAwardArgument argument = AWARD_MAPPER.toUpdateArgument(dto);

        return AWARD_MAPPER.toDto(awardService.update(argument, id));
    }
}
