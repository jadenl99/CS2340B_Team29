package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Wall;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveOffScreen;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;
import com.example.cs2340b_team29.viewmodel.MoveUp;
import com.example.cs2340b_team29.model.Knife;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Weapon;
import com.example.cs2340b_team29.viewmodel.MoveOffScreen;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;

public class PlayerAttackCollisionHandler implements CollisionObserver {

    public void onCollision(Collidable e1, Collidable e2, MoveStrategy strategy) {
        if (e1 instanceof Player && e2 instanceof Enemy) {
            Player player = (Player) e1;
            Enemy enemy = (Enemy) e2;
            enemy.setVisible(false);
        }
    }

}
