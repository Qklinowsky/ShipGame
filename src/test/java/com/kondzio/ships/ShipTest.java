package com.kondzio.ships;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.kondzio.ships.Point.point;
import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    void isViableShipPart() {
        Ship ship = new Ship(Collections.singletonList(point(1, 1)));
        Assertions.assertThat(ship.isViableShipPart(Point.point(1,2))).isTrue();
        Assertions.assertThat(ship.isViableShipPart(Point.point(1,1))).isFalse();
        Assertions.assertThat(ship.isViableShipPart(Point.point(3,4))).isFalse();

        Ship ship1 = new Ship(Arrays.asList(Point.point(1, 1), Point.point(1, 2)));
        Assertions.assertThat(ship1.isViableShipPart(Point.point(1,0))).isTrue();
        Assertions.assertThat(ship1.isViableShipPart(Point.point(1,3))).isTrue();
        Assertions.assertThat(ship1.isViableShipPart(Point.point(1,2))).isFalse();
        Assertions.assertThat(ship1.isViableShipPart(Point.point(2,1))).isFalse();
        Assertions.assertThat(ship1.isViableShipPart(Point.point(3,4))).isFalse();


    }

    @Test
    void collides1() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(point(1, 1));
        points.add(point(1, 0));
        points.add(point(1, 2));
        Ship instance = new Ship(points);
        Assertions.assertThat(instance.collides(point(1, 1))).isTrue();
        Assertions.assertThat(instance.collides(point(3, 4))).isFalse();
    }

    @Test
    void getCollidingPoints() {
        Ship instance = new Ship(Collections.EMPTY_LIST);

        List<Point> collidingPoints = instance.getCollidingPoints(point(1, 1));
        assertEquals(9, collidingPoints.size());
        Assertions.assertThat(collidingPoints).containsExactlyInAnyOrder(
                point(0, 0), point(0, 1), point(0, 2),
                point(1, 0), point(1, 1), point(1, 2),
                point(2, 0), point(2, 1), point(2, 2));
    }


}