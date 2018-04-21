package com.kondzio.ships;

public class ShipsGame {
    public static void main(String[] args) {
        GameSpecs gameSpecs = new GameSpecs(10, 10);
        gameSpecs.addShip(4, 2);
        gameSpecs.addShip(3, 3);
        gameSpecs.addShip(2, 4);
        Board board = createBoard(gameSpecs);
        GameEngine gameEngine = new GameEngine(board);
        gameEngine.startGame();


    }

    public static Board createBoard(GameSpecs gameSpecs) {
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

}
