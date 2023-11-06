package com.example.cs2340b_team29.viewmodel;

import com.example.cs2340b_team29.model.Entity;


public class MoveLeft implements MoveStrategy {

    public void move(Entity entity) {
        int currX = entity.getX();
        currX = currX - 1;
        entity.setX(currX);
    }
}
