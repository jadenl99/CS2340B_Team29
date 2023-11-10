package com.example.cs2340b_team29.model;



public class Snake extends Enemy {

    private int enemyID = 2;
    public Snake() {
        super(0, 0);
    }

    @Override
    public int getEnemyID() {
        return enemyID;
    }



    @Override
    public void move() {
        moveStrategy.move(this);
    }

    @Override
    public void attack() {

    }
}
