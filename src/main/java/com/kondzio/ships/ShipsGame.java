package com.kondzio.ships;

import com.kondzio.ships.ui.GameWindow;

public class ShipsGame {
    public static void main(String[] args) {
        GameSpecs gameSpecs = new GameSpecs(10, 10);
        gameSpecs.addShip(4, 2);
        gameSpecs.addShip(3, 3);
        gameSpecs.addShip(2, 4);
        GameEngine gameEngine = new GameEngine(gameSpecs);
        gameEngine.startGame();

    }
}
