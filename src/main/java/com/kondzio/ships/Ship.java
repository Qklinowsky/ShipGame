package com.kondzio.ships;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final List<Point> coordinates;

    public Ship(List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    public boolean collides(Point point) {
        for (Point coordinate : coordinates) {
            List<Point> possiblyCollidingPoints = getCollidingPoints(coordinate);
            if(possiblyCollidingPoints.contains(point)){
                return true;
            }
        }
        return false;
    }
    protected List<Point> getCollidingPoints(Point point){
        List<Point> points = new ArrayList<Point>();
        for (int i = point.getX()-1; i <= point.getX()+1; i++) {
            for (int j = point.getY()-1; j <= point.getY()+1; j++) {
                points.add(new Point(i,j));
            }

        }
        return points;
    }
}
