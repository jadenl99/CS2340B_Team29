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
    public void onCollision(Collidable player1, Collidable enemy, MoveStrategy moveStrategy) {
            Player player = (Player) player1;
            //decrease HP
            int difficulty = MapData.getMapData().getDifficulty();
            if (difficulty == 1) {
                player.setHpLevel(player.getHP() - 1);
            } else if (difficulty == 2) {
                player.setHpLevel(player.getHP() - 3);
            } else {
                player.setHpLevel(player.getHP() - 5);
            }
        }
    }

