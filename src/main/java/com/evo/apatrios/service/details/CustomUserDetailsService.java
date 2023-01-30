package com.evo.apatrios.service.details;

import com.evo.apatrios.exception.UserNotFoundException;
import com.evo.apatrios.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                         .orElseThrow(() -> new UserNotFoundException("User with this id is not found -" + username, null));
    }
}
