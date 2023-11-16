package com.example.cs2340b_team29.powerup;

/**
 * Concrete implementation of a PowerUp. Contains all the data to display a
 * RegenPowerUp on the map.
 */

public class RegenPowerUp extends PowerUp {

    public RegenPowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
