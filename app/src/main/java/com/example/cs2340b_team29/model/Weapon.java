package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public abstract class Weapon implements Collidable {
    protected int x;
    protected int y;
    protected Bitmap bitmap;
    protected int weaponId;
    boolean isVisible;
    public Weapon(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }
    public abstract int getWeaponId();

    public abstract void subscribe(CollisionObserver observer);
    public abstract void unsubscribe(CollisionObserver observer);

    public abstract void notifyCollision(Collidable entity, MoveStrategy moveStrategy);


    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public abstract Bitmap getBitmap();
    public abstract void setBitmap(Bitmap bitmap);
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }


}
