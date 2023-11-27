package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public class Sword extends Weapon {




    public Sword(int x, int y) {
        super(x, y);
        weaponId = 2;
    }

    public int getWeaponId() {
        return weaponId;
    }

    @Override
    public void subscribe(CollisionObserver observer) {

    }

    @Override
    public void unsubscribe(CollisionObserver observer) {

    }

    @Override
    public void notifyCollision(Collidable entity, MoveStrategy moveStrategy) {

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


}
