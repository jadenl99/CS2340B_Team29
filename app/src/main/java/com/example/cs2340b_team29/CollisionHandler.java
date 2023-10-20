package com.example.cs2340b_team29;

import com.example.cs2340b_team29.model.Entity;


import java.util.ArrayList;
// Setup for Sprint 4
public class CollisionHandler {
    private ArrayList<Entity> entities;
    private ArrayList<CollisionObserver> observers;

    public CollisionHandler() {
        this.observers = new ArrayList<>();
        this.entities = new ArrayList<>();
    }

    public void subscribe(CollisionObserver observer) {
        observers.add(observer);
    }
    public void unsubscribe(CollisionObserver observer) {
        observers.remove(observer);
    }


}
