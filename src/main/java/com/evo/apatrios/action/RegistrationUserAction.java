package com.evo.apatrios.action;

import com.evo.apatrios.api.auth.dto.AuthRequest;
import com.evo.apatrios.api.auth.dto.AuthResponse;
import com.evo.apatrios.api.auth.dto.RegisterRequest;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.argument.CreateUserArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegistrationUserAction {

    AuthorizationUserAction authorizationUserAction;

    CustomUserService userService;

    PasswordEncoder passwordEncoder;

    public AuthResponse execute(RegisterRequest request) {
        userService.create(CreateUserArgument.builder()
                                             .email(request.getEmail())
                                             .password(passwordEncoder.encode(request.getPassword()))
                                             .faculty(request.getFaculty())
                                             .fullName(request.getFullName())
                                             .studyGroup(request.getStudyGroup())
                                             .build());

        return authorizationUserAction.execute(AuthRequest.builder()
                                                          .email(request.getEmail())
                                                          .password(request.getPassword())
                                                          .build());
    }
}
