package com.kondzio.ships;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final List<ShipPart> coordinates;

    public Ship(List<Point> coordinates) {
        List<ShipPart> shipParts = new ArrayList<>();
        for (Point coordinate : coordinates) {
            ShipPart shipPart = new ShipPart(coordinate);
            shipParts.add(shipPart);
        }
        this.coordinates = shipParts;
    }

    public boolean collides(Point point) {
        for (ShipPart coordinate : coordinates) {
            List<Point> possiblyCollidingPoints = getCollidingPoints(coordinate.getPoint());
            if (possiblyCollidingPoints.contains(point)) {
                return true;
            }
        }
        return false;
    }

    protected List<Point> getCollidingPoints(Point point) {
        List<Point> points = new ArrayList<Point>();
        for (int i = point.getX() - 1; i <= point.getX() + 1; i++) {
            for (int j = point.getY() - 1; j <= point.getY() + 1; j++) {
                points.add(new Point(i, j));
            }

        }
        return points;
    }

    public ShipPart isShipPart(Point point) {
        for (ShipPart coordinate : coordinates) {
            if (coordinate.getPoint().equals(point)) {
                return coordinate;
            }
        }
        return null;
    }

    public boolean hasHealthyParts() {
        for (ShipPart coordinate : coordinates) {
            if (coordinate.getStatus() == ShipStatus.HEALTHY) {
                return true;
            }
        }
        return false;
    }
}
