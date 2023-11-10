package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

public abstract class Enemy {
    public abstract void attack();
    //each enemy implements attack differently

    public abstract int getX();

    public abstract int getY();

    public abstract void setX(int x);

    public abstract void setY(int y);

    public abstract int getEnemyID ();

    public abstract void setBitmap(Bitmap decodeResource);

    public abstract Bitmap getBitmap();
}
