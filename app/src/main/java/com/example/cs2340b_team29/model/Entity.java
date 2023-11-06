package com.example.cs2340b_team29.model;

import com.example.cs2340b_team29.collision.Collidable;

public abstract class Entity implements Collidable {
    private int x;
    private int y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void move();


}
