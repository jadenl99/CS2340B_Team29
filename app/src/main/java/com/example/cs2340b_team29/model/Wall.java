package com.example.cs2340b_team29.model;

import com.example.cs2340b_team29.collision.Collidable;

public class Wall implements Collidable {
    private int x;
    private int y;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
