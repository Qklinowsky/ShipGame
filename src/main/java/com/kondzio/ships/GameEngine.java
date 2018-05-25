package com.kondzio.ships;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.*;

public class GameEngine {
    private static final Logger log = LoggerFactory.getLogger(GameEngine.class);

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
        Callable<Board> chosePlayer1ShipsLocation = player1.choseShipsLocation(gameSpecs);
        Callable<Board> chosePlayer2ShipsLocation = player2.choseShipsLocation(gameSpecs);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Board> choosePlayer1ShipsLocationFuture = executorService.submit(chosePlayer1ShipsLocation);
        Future<Board> choosePlayer2ShipsLocationFuture = executorService.submit(chosePlayer2ShipsLocation);
        try {
            while (!choosePlayer1ShipsLocationFuture.isDone() && !choosePlayer2ShipsLocationFuture.isDone()) {
                Thread.sleep(1000);
            }
            log.info("Finished setup");
            System.out.println("Finished setup.");
            player1Board = choosePlayer1ShipsLocationFuture.get();
            log.debug("{} board = {}", player1.getName(), player1Board);
            player2Board = choosePlayer2ShipsLocationFuture.get();
            log.debug("{} board = {}", player2.getName(), player2Board);
            while (true){
                log.info("{} Round started", player1.getName());
                player1.chooseField(player2Board);
                log.info("{} Round finished", player1.getName());
                log.info("{} Round started", player2.getName());
                player2.chooseField(player1Board);
                log.info("{} Round finished", player2.getName());
            }

        } catch (Exception e) {
            log.error("Game failed.", e);
        }
    }


}
