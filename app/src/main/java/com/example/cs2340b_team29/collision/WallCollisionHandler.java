package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Wall;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;
import com.example.cs2340b_team29.viewmodel.MoveUp;

public class WallCollisionHandler implements CollisionObserver {

    public void onCollision(Collidable e1, Collidable e2, MoveStrategy moveStrategy) {
        if (e1 instanceof Player && e2 instanceof Wall) {
            Player player = (Player) e1;
            if (moveStrategy instanceof MoveDown) {
                player.setY(player.getY() - 1);
            } else if (moveStrategy instanceof MoveUp) {
                player.setY(player.getY() + 1);
            } else if (moveStrategy instanceof MoveRight) {
                player.setX(player.getX() - 1);
            } else {
                player.setX(player.getX() + 1);
            }
        }

    }
}
