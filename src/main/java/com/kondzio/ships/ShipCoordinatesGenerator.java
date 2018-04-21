package com.kondzio.ships;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShipCoordinatesGenerator {

    private static final int TRIES = 10;
    private final Random randomGenerator;

    public ShipCoordinatesGenerator(){
        this.randomGenerator = new Random();
    }

    public ShipCoordinatesGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public Ship generateShip(int size, Board board) {
        boolean collides = true;
        List<Point>coordinates = null;
        int counter = 0;
        while(collides && counter < TRIES ){
            coordinates = generateShipCoordinates(size, board);
            collides = board.doesCollideWithAnyShip(coordinates);
            counter++;
        }
        if(collides){
            throw new IllegalStateException("Failed to place new ship on board during " + TRIES + " tries.");
        }
        return new Ship(coordinates);
    }

    private List<Point> generateShipCoordinates(int size, Board board) {
        Point generatedPoint = new Point (randomGenerator.nextInt(board.getxSize()),randomGenerator.nextInt(board.getySize()));
        Direction direction = generateRandomDirection();
        ArrayList<Point> coordinates = new ArrayList<Point>();
        coordinates.add(generatedPoint);
        for (int i = 1; i < size; i++) {
            Point point = new Point(generatedPoint.getX() + direction.getX(), generatedPoint.getY() + direction.getY());
            coordinates.add(point);
        }
        return coordinates;
    }

    private Direction generateRandomDirection() {
        int randomDirection = (randomGenerator.nextInt(4));
        Direction[] values = Direction.values();
        return values[randomDirection];
    }
}
