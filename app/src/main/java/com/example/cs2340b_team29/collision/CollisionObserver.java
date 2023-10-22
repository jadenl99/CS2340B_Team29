package com.example.cs2340b_team29.collision;


import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public interface CollisionObserver {
    void onCollision(Collidable e1, Collidable e2, MoveStrategy strategy);
}
