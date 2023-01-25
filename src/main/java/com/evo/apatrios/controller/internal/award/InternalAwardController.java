package com.evo.apatrios.controller.internal.award;

import com.evo.apatrios.controller.award.dto.output.AwardDto;
import com.evo.apatrios.controller.internal.award.dto.CreateAwardDto;
import com.evo.apatrios.controller.internal.award.dto.UpdateAwardDto;
import com.evo.apatrios.service.award.AwardService;
import com.evo.apatrios.service.award.argument.CreateAwardArgument;
import com.evo.apatrios.service.award.argument.UpdateAwardArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.evo.apatrios.controller.award.mapper.AwardMapper.AWARD_MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("internal/award")
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
