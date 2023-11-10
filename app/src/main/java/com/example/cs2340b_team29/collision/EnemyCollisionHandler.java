package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Wall;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;
import com.example.cs2340b_team29.viewmodel.MoveUp;

public class EnemyCollisionHandler implements CollisionObserver{
    @Override
    public void onCollision(Collidable player1, Collidable enemy1, MoveStrategy moveStrategy) {
            Player player = (Player) player1;
            int difficulty = MapData.getMapData().getDifficulty();
            Enemy enemy = (Enemy) enemy1;
            //decrease HP
            enemy.attack(player, difficulty);
        }
    }

