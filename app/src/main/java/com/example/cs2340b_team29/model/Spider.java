package com.example.cs2340b_team29.model;


import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.collision.CollisionObserver;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

import java.util.ArrayList;

public class Spider extends Enemy {

    private int enemyID = 3;
    private ArrayList<CollisionObserver> observers;

    public Spider() {
        super(0, 0);
        observers = new ArrayList<>();
    }
    @Override

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


    public int getEnemyID() {
        return enemyID;
    }

    @Override
    public void move() {
        moveStrategy.move(this);
    }

    @Override
    public void attack(Player player, int difficulty) {
        if (difficulty == 1) {
            player.setHpLevel(player.getHP() - 2);
        } else if (difficulty == 2) {
            player.setHpLevel(player.getHP() - 4);
        } else {
            player.setHpLevel(player.getHP() - 6);
        }
    }
}
