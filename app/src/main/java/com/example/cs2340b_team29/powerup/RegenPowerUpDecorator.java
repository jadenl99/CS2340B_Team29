package com.example.cs2340b_team29.powerup;

import com.badlogic.gdx.utils.compression.lzma.Base;
import com.example.cs2340b_team29.model.Player;

public class RegenPowerUpDecorator extends PowerUpDecorator {

    public RegenPowerUpDecorator(BasePowerUpBox powerUpBox) {
        super(powerUpBox);
    }

    @Override
    public void updateBuffsToPlayer(Player player) {
    }
}
