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

    @Override
    public void attack(Player player, int difficulty) {
        if (difficulty == 1) {
            player.setHpLevel(player.getHP() - 2);
        } else if (difficulty == 2) {
            player.setHpLevel(player.getHP() - 5);
        } else {
            player.setHpLevel(player.getHP() - 7);
        }
    }
}
