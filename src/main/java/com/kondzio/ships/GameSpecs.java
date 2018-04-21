package com.kondzio.ships;

import java.util.ArrayList;
import java.util.List;

public class GameSpecs {

    private List<ShipSpec> shipSpecs = new ArrayList<>();
    private int xSize;
    private int ySize;

    public GameSpecs(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void addShip(int size, int quantity) {
        shipSpecs.add(new ShipSpec(size, quantity));
    }

    public List<ShipSpec> getShipSpecs() {
        return shipSpecs;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public static class ShipSpec {
        private int size;
        private int quantity;

        ShipSpec(int size, int quantity) {
            this.size = size;
            this.quantity = quantity;
        }

        public int getSize() {
            return size;
        }

        public int getQuantity() {
            return quantity;
        }
    }

}
