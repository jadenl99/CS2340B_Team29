package com.example.cs2340b_team29.powerup;

public class AttackPowerUp extends PowerUp {

    public AttackPowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getPowerUpId() {
        return 6;
    }
}
