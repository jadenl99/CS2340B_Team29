package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.Player;

import com.example.cs2340b_team29.viewmodel.MoveOffScreen;

import com.example.cs2340b_team29.viewmodel.MoveStrategy;


public class PlayerAttackCollisionHandler implements CollisionObserver {

    public void onCollision(Collidable e1, Collidable e2, MoveStrategy strategy) {
        if (e1 instanceof Player && e2 instanceof Enemy) {
            Player player = (Player) e1;

            if (player.getHasSword() || player.getHasKnife()) {
                MoveStrategy offScreen = new MoveOffScreen();
                Enemy enemy = (Enemy) e2;
                player.setScore(player.getScore() + 100);
                enemy.setVisible(false);
            }
        }
    }

}
