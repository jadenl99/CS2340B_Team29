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
    protected boolean isVisible;
    protected int powerUpId;
    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
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

    }

    @Override
    public void setY(int y) {

    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public abstract int getPowerUpId();
}
