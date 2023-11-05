package com.example.cs2340b_team29.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;

import com.example.cs2340b_team29.R;

public class Ninja implements Enemy {

    private int x;
    private int y;
    private Bitmap bitmap;
    private int enemyID = 1;
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
