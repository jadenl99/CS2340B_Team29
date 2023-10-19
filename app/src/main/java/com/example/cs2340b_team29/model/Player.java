package com.example.cs2340b_team29.model;

public class Player {
    private static Player player;
    private double x;
    private double y;
    private int hpLevel;
    private int score;
    private String playerName;
    private long timeDatePlayed;
    private int idAvatar;

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
    public int getHP() {

        return hpLevel;
    }

    public int getScore() {

        return score;
    }


    public void setScore(int score) {

        this.score = score;
    }

    public void setPlayerName(String name) {

        playerName = name;
    }

    public String getPlayerName() {

        return playerName;
    }

    public void setTimeDatePlayed(long tdplayed) {

        timeDatePlayed = tdplayed;
    }

    public long getTimeDatePlayed() {

        return timeDatePlayed;
    }
    public int getIdAvatar() {

        return idAvatar;
    }

    public void setHpLevel(int hp) {

        hpLevel = hp;
    }

    public void setIdAvatar(int id) {

        idAvatar = id;
    }
}