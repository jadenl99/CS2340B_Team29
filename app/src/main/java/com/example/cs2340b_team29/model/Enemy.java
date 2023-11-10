package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.Collidable;

import org.w3c.dom.Entity;

public abstract class Enemy implements Entity {
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
