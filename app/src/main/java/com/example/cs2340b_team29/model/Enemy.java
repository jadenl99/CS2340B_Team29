package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.Collidable;
// Should probably extend Entity
public abstract class Enemy extends Entity {
    public Enemy(int x, int y) {
        super(x, y);
    }

    public abstract int getEnemyID();

    // TODO add common actions of enemies here like attack()
}
