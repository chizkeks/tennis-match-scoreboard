package org.petprojects.tennis.dto;

import lombok.Getter;

@Getter
public enum GamePoints {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");

    private final String code;

    GamePoints(String code) {
        this.code = code;
    }

    public GamePoints next() {
        if (this == GamePoints.ADVANTAGE)
            throw new IllegalStateException("Cannot advance from current game point.");
        else
            return GamePoints.values()[this.ordinal() + 1];
    }

    public GamePoints previous() {
        if (this == GamePoints.ZERO)
            throw new IllegalStateException("There is no more previous values");
        else
            return GamePoints.values()[this.ordinal() - 1];
    }

}
