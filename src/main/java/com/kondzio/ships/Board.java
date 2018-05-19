package com.kondzio.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final int xSize;
    private final int ySize;
    private final List<Ship> ships;
    private int movesCount = 0;
    private List<Point> failedAttempts;


    public Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        ships = new ArrayList<>();
        failedAttempts = new ArrayList<>();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public boolean doesCollideWithAnyShip(List<Point> coordinates) {
        for (Ship ship : ships) {
            for (Point coordinate : coordinates) {
                if (ship.collides(coordinate)) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean attemptHit(Point point) {
        for (Ship ship : ships) {
            ShipPart hit = ship.isShipPart(point);
            if (hit != null) {
                hit.markHit();
                return true;
            }
        }
        return false;
    }

    public boolean hasHealthyShips() {
        for (Ship ship : ships) {
            if (ship.hasHealthyParts()) {
                return true;
            }
        }
        return false;
    }


    public void pickField(Point point) {

        if(!this.attemptHit(point)){
            failedAttempts.add(point);
        }
        movesCount++;
    }

    public ShipStatus getFieldStatus(Point point) {
        for (Ship ship : ships) {
            ShipPart shipPart = ship.isShipPart(point);
            if(shipPart != null){
                return shipPart.getStatus();
            }
        }
        if(failedAttempts.contains(point)){
            return ShipStatus.MISS;
        }else{
            return ShipStatus.EMPTY;
        }
    }

    public int getMoveCount() {
        return movesCount;
    }

    public boolean isConsistentWith(GameSpecs gameSpecs) {

        for (GameSpecs.ShipSpec shipSpec : gameSpecs.getShipSpecs()) {
            ArrayList<Ship> matchingShips = new ArrayList<>();
            for (Ship ship : this.ships) {
                if(ship.getSize() == shipSpec.getSize() ){
                    matchingShips.add(ship);
                }

            }
            if(shipSpec.getQuantity() != matchingShips.size()){
                return false;
            }
        }
        return true;
    }

    public void addShipPart(Point point) {
        ships.add(new Ship(Collections.singletonList(point)));
    }

    public void addNewPoint(Point point){
        for (Ship ship : ships) {
            if(ship.isViableShipPart(point)){
                ship.addShipPart(point);
                return;
            }


        }
        if(doesCollideWithAnyShip(Collections.singletonList(point))){
            return;
        }
        addShip(new Ship(Collections.singletonList(point)));

    }
}

