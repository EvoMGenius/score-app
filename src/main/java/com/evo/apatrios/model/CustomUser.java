package com.evo.apatrios.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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

    private String fullName;

    private String faculty;

    private String studyGroup;

    private String email;

    private Long score;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    private List<Award> awards;
}
