package com.kondzio.ships;

import net.bytebuddy.implementation.bytecode.Throw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Board {
    private final List<Ship> ships;
    private int movesCount = 0;
    private List<Point> failedAttempts;
    private GameSpecs gameSpecs;


    public Board(GameSpecs gameSpecs) {
        this.gameSpecs = gameSpecs;
        ships = new ArrayList<>();
        failedAttempts = new ArrayList<>();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public int getxSize() {
        return gameSpecs.getxSize();
    }

    public int getySize() {
        return gameSpecs.getySize();
    }

    public boolean doesCollideWithAnyShip(List<Point> coordinates) {
        return doesCollideWithAnyShip(null, coordinates);
    }

    private boolean doesCollideWithAnyShip(Ship exceptShip, List<Point> points) {
        for (Ship ship : ships) {
            for (Point coordinate : points) {
                if (ship.collides(coordinate) && ship != exceptShip) {
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

        if (!this.attemptHit(point)) {
            failedAttempts.add(point);
        }
        movesCount++;
    }

    public ShipStatus getFieldStatus(Point point) {
        for (Ship ship : ships) {
            ShipPart shipPart = ship.isShipPart(point);
            if (shipPart != null) {
                return shipPart.getStatus();
            }
        }
        if (failedAttempts.contains(point)) {
            return ShipStatus.MISS;
        } else {
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
                if (ship.getSize() == shipSpec.getSize()) {
                    matchingShips.add(ship);
                }

            }
            if (shipSpec.getQuantity() != matchingShips.size()) {
                return false;
            }
        }
        return true;
    }


    public void addNewPoint(Point point) {
        Ship potentialShip = null;
        for (Ship ship : ships) {
            if (!ship.isDesiredSize()) {
                potentialShip = ship;
                break;
            }
        }
        if (potentialShip != null) {
            if (potentialShip.isViableShipPart(point) && !doesCollideWithAnyShip(potentialShip, Collections.singletonList(point))) {

                potentialShip.addShipPart(point);
                return;
            }
        } else {
            if (doesCollideWithAnyShip(Collections.singletonList(point))) {
                return;
            } else {
                Ship ship = new Ship(getNextShipSize());
                ship.addShipPart(point);
                ships.add(ship);
            }
            return;
        }
    }

    private int getNextShipSize() {
        List<GameSpecs.ShipSpec> shipSpecs = gameSpecs.getShipSpecs();
        shipSpecs.sort(new Comparator<GameSpecs.ShipSpec>() {
            @Override
            public int compare(GameSpecs.ShipSpec o1, GameSpecs.ShipSpec o2) {
                return o2.getSize() - o1.getSize();
            }
        });
        for (GameSpecs.ShipSpec shipSpec : shipSpecs) {
            List<Ship> shipsGivenSize = new ArrayList<>();
            for (Ship ship : ships) {
                if (ship.getSize() == shipSpec.getSize()) {
                    shipsGivenSize.add(ship);
                }
            }
            if (shipsGivenSize.size() < shipSpec.getQuantity()) {
                return shipSpec.getSize();
            }

        }
        throw new IllegalStateException("No more ships to place");
    }

    @Override
    public String toString() {
        return "Board{" +
                "ships=" + ships +
                ", movesCount=" + movesCount +
                ", failedAttempts=" + failedAttempts +
                ", gameSpecs=" + gameSpecs +
                '}';
    }
}

