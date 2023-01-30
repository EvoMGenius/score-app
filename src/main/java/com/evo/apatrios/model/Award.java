package com.evo.apatrios.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Award {

    @Id
    @GeneratedValue
    @Column(name = "award_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long cost;
}
