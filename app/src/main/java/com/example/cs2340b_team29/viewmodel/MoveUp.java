package com.example.cs2340b_team29.viewmodel;


import com.example.cs2340b_team29.model.Entity;

public class MoveUp implements MoveStrategy {



    public void move(Entity entity) {
        int currY = entity.getY();
        currY = currY - 1;
        entity.setY(currY);
    }
}
