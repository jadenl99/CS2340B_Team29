package com.example.cs2340b_team29.model;


import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

import java.util.ArrayList;

public class Snake extends Enemy {

    private int enemyID = 2;
    private ArrayList<CollisionObserver> observers;
    public Snake() {
        super(0, 0);
        observers = new ArrayList<>();
    }

    @Override
    public int getEnemyID() {
        return enemyID;
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

    @Override
    public void attack(Player player, int difficulty) {
        if (difficulty == 1) {
            player.setHpLevel(player.getHP() - 1);
            player.setScore(player.getScore() - 10);
        } else if (difficulty == 2) {
            player.setHpLevel(player.getHP() - 3);
            player.setScore(player.getScore() - 30);
        } else {
            player.setHpLevel(player.getHP() - 5);
            player.setScore(player.getScore() - 50);
        }
    }
}
