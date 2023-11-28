package com.example.cs2340b_team29.powerup;


/**
 * Concrete implementation of a PowerUp. Contains all the data to display a
 * HealthPowerUp on the map.
 */
public class HealthPowerUp extends PowerUp {
    private int powerUpId = 2;

    public HealthPowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public int getPowerUpId() {
        return powerUpId;
    }


}
