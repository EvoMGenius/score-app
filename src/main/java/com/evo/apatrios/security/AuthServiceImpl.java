package com.evo.apatrios.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    @Override
    public UUID getAuthorizedUserId() {
        return null;//todo
    }
}
