package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.collision.Collidable;


import java.util.ArrayList;

public class Player implements Collidable {
    private static Player player;
    private int x;
    private int y;
    private int hpLevel;
    private int score;
    private String playerName;
    private long timeDatePlayed;
    private int idAvatar;
    private Bitmap bitmapAvatar;
    private ArrayList<CollisionObserver> observers;


    private Player() {
        x = 0;
        y = 0;
        // for now, will count down from score based on time
        score = 100;
        observers = new ArrayList<>();
    }
    public static synchronized Player getPlayer() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    @Override
    public void setX(int x) {

        this.x = x;
    }

    @Override
    public void setY(int y) {

        this.y = y;
    }

    @Override
    public int getX() {

        return x;
    }
    @Override
    public int getY() {

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
    public void setBitmapAvatar(Bitmap avatar) {
        bitmapAvatar = avatar;
    }

    public Bitmap getBitmapAvatar() {
        return bitmapAvatar;
    }

    public void subscribe(CollisionObserver observer) {
        observers.add(observer);
    }
    public void unsubscribe(CollisionObserver observer) {
        observers.remove(observer);
    }

    public void notifyCollision(Collidable entity) {
        for (CollisionObserver observer : observers) {
            observer.onCollision(this, entity);
        }
    }
}