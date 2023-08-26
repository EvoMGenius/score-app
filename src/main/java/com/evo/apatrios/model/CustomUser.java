package com.evo.apatrios.model;

import lombok.*;
import ru.themikhailz.security.CustomUserDetailsImpl;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomUser {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomUser that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
