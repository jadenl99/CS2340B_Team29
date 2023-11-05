package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.R;

public class Spider implements Enemy {
    private int x;
    private int y;
    private Bitmap bitmap;

    private int enemyID = 3;

    public Spider() {

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

    public int getEnemyID () {
        return enemyID;
    }

    @Override
    public void setBitmap(Bitmap decodeResource) {
        bitmap = decodeResource;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
