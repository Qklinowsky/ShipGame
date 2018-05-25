package com.kondzio.ships;

import com.kondzio.ships.ui.GameSetupWindow;
import com.kondzio.ships.ui.GameWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class HumanPlayer extends Player {
    private static final Logger log = LoggerFactory.getLogger(HumanPlayer.class);
    private GameWindow playerGameWindow;


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
                gameSetupWindow.close();
                return board;
            }
        };

    }

    @Override
    public void chooseField(Board enemyBoard) {
        if (playerGameWindow == null) {
            playerGameWindow = new GameWindow(enemyBoard);
        }
        playerGameWindow.yourTurn();
        while (!playerGameWindow.isDone()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error("Failed waiting.", e);
            }
        }
    }
}
