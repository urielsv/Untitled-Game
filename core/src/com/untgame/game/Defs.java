package com.untgame.game;

public enum Defs {

    SCREEN_WIDTH(1280),
    SCREEN_HEIGHT(720),
    REFRESH_RATE(60);

    private final int value;
    Defs (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
