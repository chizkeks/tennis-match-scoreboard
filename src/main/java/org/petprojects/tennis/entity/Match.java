package org.petprojects.tennis.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match implements BaseEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "player1")
    private Player firstPlayer;
    @ManyToOne
    @JoinColumn(name = "player2")
    private Player secondPlayer;
    @ManyToOne
    @JoinColumn(name = "winner")
    private Player winner;

}
