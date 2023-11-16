package com.example.cs2340b_team29.powerup;

import com.example.cs2340b_team29.model.Player;

/**
 * Interface for both wrappers and wrapped objects
 */
public interface BasePowerUpBox {
    abstract void updateBuffsToPlayer(Player player);
}
