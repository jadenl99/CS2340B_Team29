package com.example.cs2340b_team29.viewmodel;

import com.example.cs2340b_team29.model.Entity;

public class MoveOffScreen implements MoveStrategy {

    public void move(Entity entity) {
        int currY = entity.getY();
        currY += 23;
        int currX = entity.getX();
        currX += 12;
        entity.setY(currY);
        entity.setX(currX);
    }
}
