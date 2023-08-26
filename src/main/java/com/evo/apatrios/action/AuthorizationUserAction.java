package com.evo.apatrios.action;

import com.evo.apatrios.controller.auth.dto.AuthRequest;
import com.evo.apatrios.controller.auth.dto.AuthResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.themikhailz.security.CustomUserDetailsImpl;
import ru.themikhailz.service.JwtService;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorizationUserAction {

    JwtService jwtService;

    AuthenticationManager authenticationManager;

    public AuthResponse execute(AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authenticate.getPrincipal();

        String token = jwtService.generateToken(userDetails);
        return AuthResponse.builder()
                           .token(token)
                           .build();
    }
}
