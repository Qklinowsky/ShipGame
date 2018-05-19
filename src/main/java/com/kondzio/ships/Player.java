package com.kondzio.ships;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public abstract class Player {
    private int score;
    private String name;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public abstract Callable<Board> choseShipsLocation(GameSpecs gameSpecs);
}
