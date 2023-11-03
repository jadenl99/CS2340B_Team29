package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

public class Ninja implements Enemy {
    private int x;
    private int y;
    private Bitmap bitmap;
    public Ninja() {

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
