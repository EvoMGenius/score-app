package com.evo.apatrios.security;

import com.evo.apatrios.controller.auth.dto.AuthRequest;
import com.evo.apatrios.controller.auth.dto.AuthResponse;
import com.evo.apatrios.controller.auth.dto.RegisterRequest;
import lombok.NonNull;

import java.util.UUID;

public interface AuthService {
    UUID getAuthorizedUserId();

    AuthResponse register(@NonNull RegisterRequest request);

    AuthResponse authenticate(@NonNull AuthRequest request);
}
