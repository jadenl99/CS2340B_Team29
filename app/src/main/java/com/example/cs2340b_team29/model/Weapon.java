package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public abstract class Weapon implements Collidable {
    Bitmap bitmap;
    int weaponId;
    boolean isVisible;
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

}
