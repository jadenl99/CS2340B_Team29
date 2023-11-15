package com.example.cs2340b_team29.powerup;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.Collidable;

/**
 * Abstract class for PowerUps. The concrete implementations for the PowerUps
 * will be used to store bitmaps and position on the map for each PowerUp.
 */
public abstract class PowerUp implements Collidable {
    protected int x;
    protected int y;
    protected Bitmap bitmap;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
