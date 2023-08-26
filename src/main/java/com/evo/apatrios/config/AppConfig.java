package com.evo.apatrios.config;

import com.evo.apatrios.repository.CustomUserRepository;
import com.evo.apatrios.service.user.CustomUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.themikhailz.annotation.EnableJwtSecurity;

@Configuration
@RequiredArgsConstructor
@EnableJwtSecurity
public class AppConfig {

    private final CustomUserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() { //можно заменить кастомным UserDetailsService
        return new CustomUserServiceImpl(repository);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}