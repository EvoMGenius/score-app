package com.evo.apatrios.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.themikhailz.security.CustomUserDetailsImpl;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomUser implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String faculty;

    @Column(nullable = false)
    private String studyGroup;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Long score;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER,
               cascade = CascadeType.ALL)
    private List<CustomUserBoughtAward> awards;

    public static CustomUserDetailsImpl getUserDetails(CustomUser byEmail) {
        return CustomUserDetailsImpl.builder().build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    //    По хорошему создать для каждого булеана поле и возвращать его.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
