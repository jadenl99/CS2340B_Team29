package com.example.cs2340b_team29.powerup;


/**
 * Concrete implementation of a PowerUp. Contains all the data to display a
 * VaporizeEnemyPowerUp on the map.
 */
public class VaporizeEnemyPowerUp extends PowerUp {
    private int powerUpId = 1;

    public VaporizeEnemyPowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public int getPowerUpId() {
        return powerUpId;
    }


}

