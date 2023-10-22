package com.example.cs2340b_team29.viewmodel;

import com.example.cs2340b_team29.LevelView;
import com.example.cs2340b_team29.model.Player;

public class MoveUp implements MoveStrategy {

    private Player player1;

    private double playerX;

    private double playerY;

    public void move(int currX, int currY) {
        currY -= currY;
    }
}
