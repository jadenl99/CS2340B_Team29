package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public class Sword extends Weapon {


    private int weaponId = 2;
    private Bitmap bitmap;

    public Sword() {
        super(8, 16);
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
