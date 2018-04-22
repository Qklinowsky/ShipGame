package com.kondzio.ships;

public class ShipPart {
    private final Point point;
    private ShipStatus status;

    public ShipPart(Point point) {
        this.point = point;
        status = ShipStatus.HEALTHY;
    }

    public Point getPoint() {
        return point;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public void markHit() {
        status = ShipStatus.HIT;
    }
}
