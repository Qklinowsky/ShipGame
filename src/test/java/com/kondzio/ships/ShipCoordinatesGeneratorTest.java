package com.kondzio.ships;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShipCoordinatesGeneratorTest {
    @Test
    void generateShip() {
        ShipCoordinatesGenerator instance = new ShipCoordinatesGenerator();
        Board board = new Board(2,2);
        Ship ship = instance.generateShip(1, board);
        Assertions.assertThat(ship).isNotNull();

    }


    @Test
    void generateShipShouldSucessfullyPlaceShip() {
        Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(0);


        ShipCoordinatesGenerator instance = new ShipCoordinatesGenerator(randomMock);
        Board board = new Board(4,4);
        board.addShip(new Ship(Collections.singletonList(Point.point(3,3))));
        Ship ship = instance.generateShip(1, board);
        Assertions.assertThat(ship).isNotNull();

    }
    @Test
    void generateShipShouldThrowExeptionWhenNoSpaceLeft() {
        Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(0);

        ShipCoordinatesGenerator instance = new ShipCoordinatesGenerator(randomMock);
        Board board = new Board(2,2);
        board.addShip(new Ship(Collections.singletonList(Point.point(0,0))));
        Assertions.assertThatThrownBy(() -> instance.generateShip(1,board)).hasMessage("Failed to place new ship on board during " + 10 + " tries.");

    }
}