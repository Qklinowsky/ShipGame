package com.kondzio.ships;

import com.kondzio.ships.ui.GameSetupWindow;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class HumanPlayer extends Player {


    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Callable<Board> choseShipsLocation(GameSpecs gameSpecs) {
        return new Callable<Board>() {
            @Override
            public Board call() throws Exception {
                Board board = new Board(gameSpecs.getxSize(), gameSpecs.getySize());
                GameSetupWindow gameSetupWindow = new GameSetupWindow(gameSpecs, board);
                while (!board.isConsistentWith(gameSpecs)) {
                    Thread.sleep(1000);
                }
                return board;
            }
        };

    }
}
