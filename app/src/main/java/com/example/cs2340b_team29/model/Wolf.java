package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

public class Wolf extends Enemy {
    private int x;
    private int y;
    private Bitmap bitmap;

    private int enemyID = 4;
    public Wolf() {

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

    public Bitmap getBitmap () {
        return bitmap;
    }

    public int getEnemyID () {
        return enemyID;
    }

    @Override
    public void setBitmap(Bitmap decodeResource) {
        bitmap = decodeResource;
    }

    @Override
    public void attack() {

    }
}
