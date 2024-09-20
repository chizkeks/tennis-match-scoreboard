package org.petprojects.tennis.dto;

import lombok.Data;

@Data
public class Score<T> {
    private T firstPlayerScore;
    private T secondPlayerScore;
}
