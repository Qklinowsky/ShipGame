package com.kondzio.ships;

public enum Direction {
    TOP(0,-1),
    BOTTOM(0,1),
    LEFT(-1,0),
    RIGHT(1,0);

    private final int y;
    private final int x;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
