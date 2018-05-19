package com.kondzio.ships;

import java.util.concurrent.Callable;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        super("Computer");
    }

    @Override
    public Callable<Board> choseShipsLocation(GameSpecs gameSpecs) {
        return new Callable<Board>() {
            @Override
            public Board call() throws Exception {
                Board board = new Board(gameSpecs.getxSize(), gameSpecs.getySize());
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
}