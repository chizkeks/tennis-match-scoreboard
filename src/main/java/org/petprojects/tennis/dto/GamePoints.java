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
            throw new IllegalStateException("Can not call next() on ADVANTAGE value, cause it is the last value");
        else
            return GamePoints.values()[this.ordinal() + 1];
    }

}
