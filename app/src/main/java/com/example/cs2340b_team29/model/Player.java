package com.example.cs2340b_team29.model;

import android.graphics.Bitmap;

import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;


import java.util.ArrayList;

public class Player extends Entity {
    private static Player player;
    private int hpLevel;
    private int score;
    private String playerName;
    private long timeDatePlayed;
    private int idAvatar;
    private ArrayList<CollisionObserver> observers;

    private MoveStrategy moveStrategy;
    private int difficulty;


    private Player() {
        super(0, 0);
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

    public int getDifficulty() {
        return difficulty;
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

    public void setDifficulty(int diff) {
        difficulty = diff;
    }

    public void setIdAvatar(int id) {

        idAvatar = id;
    }




    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void subscribe(CollisionObserver observer) {
        observers.add(observer);
    }
    public void unsubscribe(CollisionObserver observer) {
        observers.remove(observer);
    }

    public void notifyCollision(Collidable entity, MoveStrategy moveStrategy) {
        for (CollisionObserver observer : observers) {
            observer.onCollision(this, entity, moveStrategy);
        }
    }

    @Override
    public void move() {
        moveStrategy.move(this);
    }
}