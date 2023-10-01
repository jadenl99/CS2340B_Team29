package com.example.cs2340b_team29;

public class Player {
    private static Player player;
    private double x;
    private double y;
    private int score;

    private Player() {
        x = 0;
        y = 0;
        // for now, will count down from score based on time
        score = 100;

    }
    public static synchronized Player getPlayer() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score;
    }
}
