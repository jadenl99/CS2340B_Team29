package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.powerup.AttackPowerUp;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public class PowerUpCollisionHandler implements CollisionObserver {
    @Override
    public void onCollision(Collidable e1, Collidable e2, MoveStrategy strategy) {
        if (!(e2 instanceof PowerUp)) {
            return;
        }
        Player player = (Player) e1;
        PowerUp powerUp = (PowerUp) e2;

        if (powerUp instanceof AttackPowerUp) {

        }

    }
}
