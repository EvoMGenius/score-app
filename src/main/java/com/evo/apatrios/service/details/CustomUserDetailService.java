package com.evo.apatrios.service.details;

import com.evo.apatrios.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final CustomUserRepository repository;

    //todo create class that impl userdetail and contains customuser + fill authdtos
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null ;//repository.findByEmail(username); //todo mapping to customUserDetails.
    }
}
