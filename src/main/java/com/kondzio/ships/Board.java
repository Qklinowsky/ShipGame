package com.kondzio.ships;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int xSize;
    private final int ySize;
    private final List<Ship> ships;

    public Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        ships = new ArrayList<Ship>();
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

    public boolean isHit(Point point) {
        for (Ship ship : ships) {
            ShipPart hit = ship.isHit(point);
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
}

