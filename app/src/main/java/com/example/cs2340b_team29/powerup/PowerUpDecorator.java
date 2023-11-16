package com.example.cs2340b_team29.powerup;


import com.example.cs2340b_team29.model.Player;

public class PowerUpDecorator implements BasePowerUpBox {

    public BasePowerUpBox powerUpBox;
    public PowerUpDecorator(BasePowerUpBox powerUpBox) {
        this.powerUpBox = powerUpBox;
    }
    @Override
    public void updateBuffsToPlayer(Player player) {
<<<<<<< HEAD
        powerUpBox.updateBuffsToPlayer(player);
=======
>>>>>>> a944f4f (pull from main)
    }
}
