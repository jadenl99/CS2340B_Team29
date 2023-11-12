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
        //don't let player and enemy go into same box
        if (player1 instanceof Player && enemy1 instanceof Enemy) {
            Player player = (Player) player1;
            if (moveStrategy instanceof MoveDown) {
                player.setY(player.getY() - 1);
            } else if (moveStrategy instanceof MoveUp) {
                player.setY(player.getY() + 1);
            } else if (moveStrategy instanceof MoveRight) {
                player.setX(player.getX() - 1);
            } else {
                player.setX(player.getX() + 1);
            }
        }
            Player player = (Player) player1;
            int difficulty = MapData.getMapData().getDifficulty();
            if (enemy1 instanceof Enemy) {
                Enemy enemy = (Enemy) enemy1;
                //decrease HP
                enemy.attack(player, difficulty);
            }

        }
    }

