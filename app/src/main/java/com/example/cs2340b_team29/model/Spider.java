package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.R;

public class Spider extends Enemy {

    private int enemyID = 3;

    public Spider() {
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
    public void attack(Player player, int difficulty) {
        if (difficulty == 1) {
            player.setHpLevel(player.getHP() - 2);
        } else if (difficulty == 2) {
            player.setHpLevel(player.getHP() - 4);
        } else {
            player.setHpLevel(player.getHP() - 6);
        }
    }
}
