package com.kondzio.ships;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;

public class ComputerPlayer extends Player {
    private static final Logger log = LoggerFactory.getLogger(ComputerPlayer.class);
    private static final Random random = new Random();

    public ComputerPlayer() {
        super("Computer");
    }

    @Override
    public Callable<Board> choseShipsLocation(GameSpecs gameSpecs) {
        return new Callable<Board>() {
            @Override
            public Board call() throws Exception {
                Board board = new Board(gameSpecs);
                ShipCoordinatesGenerator shipGenerator = new ShipCoordinatesGenerator();
                for (GameSpecs.ShipSpec shipSpec : gameSpecs.getShipSpecs()) {
                    for (int i = 0; i < shipSpec.getQuantity(); i++) {
                        Ship ship = shipGenerator.generateShip(shipSpec.getSize(), board);
                        board.addShip(ship);
                    }
                }
                return board;
            }
        };

    }

    @Override
    public void chooseField(Board enemyboard) {
        int x = random.nextInt(enemyboard.getxSize());
        int y = random.nextInt(enemyboard.getySize());
        Point point = Point.point(x, y);
        log.info("Computer playter picked {}", point);
        enemyboard.pickField(point);

    }
}