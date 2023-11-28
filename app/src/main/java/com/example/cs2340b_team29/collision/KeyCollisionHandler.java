package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Key;
import com.example.cs2340b_team29.model.Player;


import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public class KeyCollisionHandler implements CollisionObserver {
    @Override
    public void onCollision(Collidable e1, Collidable e2, MoveStrategy strategy) {

        if (!(e2 instanceof Key)) {
            return;

        }
        Player player = (Player) e1;

        Key key = (Key) e2;
        player.setScore(player.getScore() + 50);
        key.setVisible(false);


    }
}