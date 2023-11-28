package com.example.cs2340b_team29.collision;

import com.example.cs2340b_team29.model.Knife;
import com.example.cs2340b_team29.model.Player;

import com.example.cs2340b_team29.model.Weapon;

import com.example.cs2340b_team29.viewmodel.MoveStrategy;


public class WeaponCollisionHandler implements CollisionObserver {

    public void onCollision(Collidable e1, Collidable e2, MoveStrategy strategy) {
        if (e1 instanceof Player && e2 instanceof Weapon) {
            Player player = (Player) e1;
            Weapon weapon = (Weapon) e2;
            if (e2 instanceof Knife) {
                player.setHasKnife(true);
            } else {
                player.setHasSword(true);
            }
            weapon.setVisible(false);
        }
    }
}
