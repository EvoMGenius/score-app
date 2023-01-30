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
public class CustomUserBoughtAward {

    @Id
    @GeneratedValue
    @Column(name = "bought_award_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "award_id", nullable = false)
    private Award award;

    private boolean isReceived;
}
