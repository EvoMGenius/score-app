package com.evo.apatrios.api.auth;

import com.evo.apatrios.action.AuthorizationUserAction;
import com.evo.apatrios.action.RegistrationUserAction;
import com.evo.apatrios.api.auth.dto.AuthRequest;
import com.evo.apatrios.api.auth.dto.AuthResponse;
import com.evo.apatrios.api.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final RegistrationUserAction registrationUserAction;

    private final AuthorizationUserAction authorizationUserAction;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(registrationUserAction.execute(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authorizationUserAction.execute(request));
    }
}