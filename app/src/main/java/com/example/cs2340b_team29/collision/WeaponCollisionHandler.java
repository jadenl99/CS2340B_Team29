package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Gun;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Wall;
import com.example.cs2340b_team29.model.Weapon;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;
import com.example.cs2340b_team29.viewmodel.MoveUp;

public class WeaponCollisionHandler implements CollisionObserver {

    public void onCollision(Collidable e1, Collidable e2, MoveStrategy strategy) {
        if (e1 instanceof Player && e2 instanceof Weapon) {
            Player player = (Player) e1;
            if (e2 instanceof Gun) {
                player.setHasGun(true);
            } else {
                player.setHasSword(true);;
            }
        }
    }
}