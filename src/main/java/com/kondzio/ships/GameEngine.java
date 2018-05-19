package com.kondzio.ships;

import java.util.Scanner;
import java.util.concurrent.*;

public class GameEngine {
    private final GameSpecs gameSpecs;
    private Board player1Board;
    private Board player2Board;
    private Player player1;
    private Player player2;

    public GameEngine(GameSpecs gameSpecs) {
        this.gameSpecs = gameSpecs;
        this.player1 = new HumanPlayer("player1");
        this.player2 = new ComputerPlayer();
    }

    public void startGame() {
        Callable<Board> choseShipsLocation = player1.choseShipsLocation(gameSpecs);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Board> chooseShipsLocationFuture = executorService.submit(choseShipsLocation);
        try {
            while (!chooseShipsLocationFuture.isDone()) {
                Thread.sleep(1000);
            }
            System.out.println("Finished setup.");
            player1Board = chooseShipsLocationFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
