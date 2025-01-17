package com.example.cs2340b_team29.model;


import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.collision.Collidable;

import com.example.cs2340b_team29.powerup.BasePowerUpBox;
import com.example.cs2340b_team29.powerup.PowerUpBox;

import com.example.cs2340b_team29.viewmodel.MoveStrategy;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private boolean isInvincible;
    private boolean hasKnife;
    private boolean hasSword;

    private BasePowerUpBox powerUpBox;
    private boolean isAttacking;



    private Player() {
        super(0, 0);
        // for now, will count down from score based on time
        score = 100;
        isInvincible = false;
        observers = new ArrayList<>();
        hasKnife = false;
        hasSword = false;
        powerUpBox = new PowerUpBox();
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
    public boolean getIsAttacking() {
        return isAttacking;
    }

    public int getDifficulty() {
        return difficulty;
    }


    public void setScore(int score) {

        this.score = Math.max(score, 0);
    }

    public void setPlayerName(String name) {

        playerName = name;
    }

    public void setIsAttacking(boolean bool) {

        isAttacking = bool;
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

        hpLevel = Math.max(hp, 0);
    }

    public void setDifficulty(int diff) {
        difficulty = diff;
    }

    public void setIdAvatar(int id) {

        idAvatar = id;
    }

    public void setHasKnife(boolean bool) {
        this.hasKnife = bool;
    }

    public void setHasSword(boolean bool) {
        this.hasSword = bool;
    }

    public boolean getHasKnife() {
        return hasKnife;
    }
    public boolean getHasSword() {
        return hasSword;
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
    public boolean getIsInvincible() {
        return isInvincible;
    }

    public void toggleIsInvincible() {
        isInvincible = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Update the variable after the delay
                isInvincible = false;
                // Cancel the timer after the task is executed
                timer.cancel();
            }
        }, 1000);
    }
    public void setIsInvincible(boolean i) {
        isInvincible = i;
    }

    public BasePowerUpBox getPowerUpBox() {
        return powerUpBox;
    }

    public void setPowerUpBox(BasePowerUpBox powerUpBox) {
        this.powerUpBox = powerUpBox;
    }

    public void updateBuffs() {
        powerUpBox.updateBuffsToPlayer(this);
    }

    public void attackEnemy() {
        //run collision handler to move enemy offscreen
    }
}