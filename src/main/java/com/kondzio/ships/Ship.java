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

    public int getSize() {
        return coordinates.size();
    }

    public boolean isViableShipPart(Point point) {
        if (coordinates.size() >= 2) {
            int xMin = Integer.MAX_VALUE;
            int xMax = 0;
            int yMin = Integer.MAX_VALUE;
            int yMax = 0;
            for (ShipPart coordinate : coordinates) {
                int x = coordinate.getPoint().getX();
                int y = coordinate.getPoint().getY();

                if (x < xMin) {
                    xMin = x;
                }
                if (x > xMax) {
                    xMax = x;
                }
                if (y < yMin) {
                    yMin = y;
                }
                if (y > yMax) {
                    yMax = y;
                }
            }
            if (xMin == xMax) {
                Point nextPossiblePart = Point.point(xMin, yMin - 1);
                Point nextPossiblePart1 = Point.point(xMin, yMax + 1);

                return point.equals(nextPossiblePart) || point.equals(nextPossiblePart1);
            }
            if (yMin == yMax) {
                Point nextPossiblePart = Point.point(xMin - 1, yMin);
                Point nextPossiblePart1 = Point.point(xMax + 1, yMin);

                return point.equals(nextPossiblePart) || point.equals(nextPossiblePart1);
            }

        } else {
            ShipPart shipPart = coordinates.get(0);
            int x = shipPart.getPoint().getX();
            int y = shipPart.getPoint().getY();
            Point nextPossiblePart = Point.point(x + 1, y);
            Point nextPossiblePart1 = Point.point(x - 1, y);
            Point nextPossiblePart2 = Point.point(x, y + 1);
            Point nextPossiblePart3 = Point.point(x, y - 1);

            return point.equals(nextPossiblePart) || point.equals(nextPossiblePart1) || point.equals(nextPossiblePart2) ||
                    point.equals(nextPossiblePart3);
        }
        return false;
    }

    public void addShipPart(Point point) {
        coordinates.add(new ShipPart(point));
    }
}
