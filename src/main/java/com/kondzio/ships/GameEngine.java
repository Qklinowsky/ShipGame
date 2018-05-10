package com.kondzio.ships;

import java.util.Scanner;

public class GameEngine {
    private Board board;
    private ChoiceTranslator translator = new ChoiceTranslator();
    private Scanner scanner = new Scanner(System.in);

    public GameEngine(Board board) {
        this.board = board;
    }

    public void startGame() {
        while (board.hasHealthyShips()) {
            System.out.println("Podaj koordynaty!");
            String choice = scanner.nextLine();
            boolean isHit = board.attemptHit(translator.translate(choice));
            if (isHit) {
                System.out.println("Trafiony");
            } else {
                System.out.println("Pudło");
            }
        }

    }
}
