package com.evo.apatrios.controller.user.dto.output;

import com.evo.apatrios.controller.award.dto.output.AwardDto;
import com.evo.apatrios.model.Award;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String fullName;

    private String faculty;

    private String studyGroup;

    private String email;

    private Long score;

    private List<AwardDto> awards;
}
