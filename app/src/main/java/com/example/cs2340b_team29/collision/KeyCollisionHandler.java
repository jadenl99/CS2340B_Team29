package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Key;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.powerup.HealthPowerUp;
import com.example.cs2340b_team29.powerup.HealthPowerUpDecorator;
import com.example.cs2340b_team29.powerup.BasePowerUpBox;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.powerup.RegenPowerUp;
import com.example.cs2340b_team29.powerup.RegenPowerUpDecorator;
import com.example.cs2340b_team29.powerup.VaporizeEnemyPowerUp;
import com.example.cs2340b_team29.powerup.VaporizeEnemyPowerUpDecorator;


import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public class KeyCollisionHandler implements CollisionObserver {
    @Override
    public void onCollision(Collidable e1, Collidable e2, MoveStrategy strategy) {

        if (!(e2 instanceof Key)) {
            return;

        }
        Player player = (Player) e1;

        Key key = (Key) e2;
        player.setScore(player.getScore() + 50);
        key.setVisible(false);


    }
}