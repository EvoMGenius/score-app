package com.evo.apatrios.security;

import com.evo.apatrios.controller.auth.dto.AuthRequest;
import com.evo.apatrios.controller.auth.dto.AuthResponse;
import com.evo.apatrios.controller.auth.dto.RegisterRequest;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.service.user.CustomUserService;
import com.evo.apatrios.service.user.argument.CreateUserArgument;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomUserService service; //todo swap to service

    //todo add customuserdetailservice

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public UUID getAuthorizedUserId() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser details = (CustomUser) authentication.getPrincipal();
        return details.getId();
    }

    @Override
    public AuthResponse register(@NonNull RegisterRequest request) {
        CustomUser user = service.create(CreateUserArgument.builder()
                                                           .fullName(request.getFullName())
                                                           .faculty(request.getFaculty())
                                                           .studyGroup(request.getStudyGroup())
                                                           .email(request.getEmail())
                                                           .password(passwordEncoder.encode(request.getPassword()))
                                                           .build());
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                           .token(jwtToken)
                           .build();
    }

    @Override
    public AuthResponse authenticate(@NonNull AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = service.findByEmail(request.getEmail());


        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                           .token(jwtToken)
                           .build();
    }
}
