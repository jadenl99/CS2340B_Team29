package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

// Should probably extend Entity
public abstract class Enemy extends Entity {
    public Enemy(int x, int y) {
        super(x, y);
    }

    public abstract int getEnemyID();
    public abstract void attack(Player player, int difficulty);
    //each enemy implements attack differently

    public abstract void subscribe(CollisionObserver observer);
    public abstract void unsubscribe(CollisionObserver observer);

    public abstract void notifyCollision(Collidable entity, MoveStrategy moveStrategy);

}
