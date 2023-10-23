package com.example.cs2340b_team29.viewmodel;



import com.example.cs2340b_team29.model.Player;

public class MoveDown implements MoveStrategy {
    private Player player1 = Player.getPlayer();

    private double playerX;

    private double playerY;

    public void move() {
        int currY = player1.getY();
        currY = currY + 1;
        player1.setY(currY);

    }

}
