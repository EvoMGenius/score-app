package com.evo.apatrios.service.user.details;

import com.evo.apatrios.exception.UserNotFoundException;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.themikhailz.security.CustomUserDetailsImpl;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomUserRepository repository;

    @Override
    public CustomUserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        return CustomUser.getUserDetails(repository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found", null)));
    }
}
