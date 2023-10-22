package com.example.cs2340b_team29.viewmodel;

import com.example.cs2340b_team29.model.Player;

public class MoveRight implements MoveStrategy {
    private Player player1 = Player.getPlayer();

    private double playerX;

    private double playerY;

    public void move() {
        int currX = player1.getX();
        currX = currX + 1;
        player1.setX(currX);
    }

}
