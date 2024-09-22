package org.petprojects.tennis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score<T> {
    private T firstPlayerScore;
    private T secondPlayerScore;
}
