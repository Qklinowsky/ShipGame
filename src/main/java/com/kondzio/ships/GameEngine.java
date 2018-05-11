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
            board.pickField(translator.translate(choice));
        }

    }
}
