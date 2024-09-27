package org.petprojects.tennis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinishedMatchDto {
    private int id;
    private PlayerDto firstPlayer;
    private PlayerDto secondPlayer;
    private PlayerDto winner;
}
