package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.powerup.HealthPowerUp;
import com.example.cs2340b_team29.powerup.HealthPowerUpDecorator;
import com.example.cs2340b_team29.powerup.BasePowerUpBox;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.powerup.RegenPowerUp;
import com.example.cs2340b_team29.powerup.RegenPowerUpDecorator;
import com.example.cs2340b_team29.powerup.VaporizeEnemyPowerUp;
import com.example.cs2340b_team29.powerup.VaporizeEnemyPowerUpDecorator;
import com.example.cs2340b_team29.powerup.AttackPowerUp;
import com.example.cs2340b_team29.powerup.PowerUp;
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


        BasePowerUpBox powerUpBox = player.getPowerUpBox();
        if (powerUp instanceof HealthPowerUp) {
            powerUpBox = new HealthPowerUpDecorator(powerUpBox);
        } else if (powerUp instanceof RegenPowerUp) {
            powerUpBox = new RegenPowerUpDecorator(powerUpBox);
        } else if (powerUp instanceof VaporizeEnemyPowerUp) {
            powerUpBox = new VaporizeEnemyPowerUpDecorator(powerUpBox);
        }

        // decorate player's powerUpBox with the new powerUp
        player.setPowerUpBox(powerUpBox);
        // make visual powerUp invisible
        powerUp.setVisible(false);

        if (powerUp instanceof AttackPowerUp) {

        }
        if (powerUp instanceof AttackPowerUp) {

        }
    }
}
