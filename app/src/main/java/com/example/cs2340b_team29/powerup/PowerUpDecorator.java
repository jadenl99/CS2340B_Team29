package com.example.cs2340b_team29.powerup;


import com.example.cs2340b_team29.model.Player;

public class PowerUpDecorator implements BasePowerUpBox {

    public BasePowerUpBox powerUpBox;
    public PowerUpDecorator(BasePowerUpBox powerUpBox) {
        this.powerUpBox = powerUpBox;
    }
    @Override
    public void updateBuffsToPlayer(Player player) {
        powerUpBox.updateBuffsToPlayer(player);
<<<<<<< HEAD
        powerUpBox.updateBuffsToPlayer(player);
=======
>>>>>>> ba194d6 (adds avatar with weapons bitmaps)
    }
}
