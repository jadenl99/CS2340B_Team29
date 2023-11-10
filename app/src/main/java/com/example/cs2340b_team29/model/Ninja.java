package com.example.cs2340b_team29.model;

public class Ninja extends Enemy {

    private int enemyID = 1;
    public Ninja() {
        super(0, 0);
    }


    public int getEnemyID() {
        return enemyID;
    }



    @Override
    public void move() {
        moveStrategy.move(this);
    }

    @Override
    public void attack(Player player, int difficulty) {
        if (difficulty == 1) {
            player.setHpLevel(player.getHP() - 1);
        } else if (difficulty == 2) {
            player.setHpLevel(player.getHP() - 2);
        } else {
            player.setHpLevel(player.getHP() - 3);
        }
    }
}
