package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;


import com.example.cs2340b_team29.viewmodel.MoveStrategy;


public class EnemyCollisionHandler implements CollisionObserver {
    @Override
    public void onCollision(Collidable player1, Collidable enemy1, MoveStrategy moveStrategy) {
        Player player = (Player) player1;
        int difficulty = MapData.getMapData().getDifficulty();
        if (enemy1 instanceof Enemy) {
            Enemy enemy = (Enemy) enemy1;
            enemy.attack(player, difficulty);
        }
    }
}

