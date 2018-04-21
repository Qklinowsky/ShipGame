package com.kondzio.ships;

import java.util.Scanner;

public class GameEngine {
    private Board board;
    private Scanner scanner = new Scanner(System.in);
    public GameEngine(Board board) {
        this.board = board;
    }

    public void startGame() {
        System.out.println("Podaj koordynaty!");
        String choice = scanner.nextLine();

    }
}
