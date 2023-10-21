package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Wall;

public class WallCollisionHandler implements CollisionObserver {

    public void onCollision(Collidable e1, Collidable e2) {
        if (e1 instanceof Player && e2 instanceof Wall) {

        }

    }
}
