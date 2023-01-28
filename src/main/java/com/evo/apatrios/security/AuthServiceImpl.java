package com.evo.apatrios.security;

import com.evo.apatrios.controller.auth.dto.AuthRequest;
import com.evo.apatrios.controller.auth.dto.AuthResponse;
import com.evo.apatrios.controller.auth.dto.RegisterRequest;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.Role;
import com.evo.apatrios.repository.CustomUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final CustomUserRepository repository; //todo swap to service

    //todo add customuserdetailservice

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public UUID getAuthorizedUserId() {
        return null;
    }

    @Override
    public AuthResponse register(@NonNull RegisterRequest request) {
//        var user = CustomUser.builder()
//                             .fullName(request.getFullName())
//                             .email(request.getEmail())
//                             .password(passwordEncoder.encode(request.getPassword()))
//                             .role(Role.USER)
//                             .build();
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthResponse.builder()
//                           .token(jwtToken)
//                           .build();
        return null;
    }

    @Override
    public AuthResponse authenticate(@NonNull AuthRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
//
//        var jwtToken = jwtService.generateToken(user);
//        return AuthResponse.builder()
//                           .token(jwtToken)
//                           .build();
        return null;
    }
}
