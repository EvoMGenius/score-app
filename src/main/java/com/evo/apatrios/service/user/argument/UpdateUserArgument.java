package com.evo.apatrios.service.user.argument;

import com.evo.apatrios.model.Role;
import com.evo.apatrios.model.CustomUserBoughtAward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class UpdateUserArgument {

    String fullName;

    String faculty;

    String studyGroup;

    String email;

    Long score;

    String password;

    Role role;

    List<CustomUserBoughtAward> awards;
}
