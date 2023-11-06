package com.example.cs2340b_team29.model;



public class Wolf extends Enemy {


    private int enemyID = 4;
    public Wolf() {
        super(0, 0);
    }


    public int getEnemyID() {
        return enemyID;
    }


    @Override
    public void move() {
        moveStrategy.move(this);
    }
}
