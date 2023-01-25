package com.evo.apatrios.service.user.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserArgument {

    String fullName;

    String faculty;

    String studyGroup;

    String email;

    String password;
}
