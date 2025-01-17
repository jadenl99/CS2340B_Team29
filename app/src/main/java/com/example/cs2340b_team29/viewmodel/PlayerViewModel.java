package com.example.cs2340b_team29.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.model.Door;
import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.Key;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Wall;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.model.Weapon;


import java.util.ArrayList;

public class PlayerViewModel extends ViewModel {
    private Player player;
    private MapData mapData;

    private boolean changeLevel;



    public PlayerViewModel() {
        player = Player.getPlayer();
        mapData = MapData.getMapData();
    }

    public void move() {
        player.move();
    }


    public void changeScore(int score) {
        int newScore = (int) (Math.max(0, score + player.getScore()));
        player.setScore(newScore);
    }

    public Player getPlayer() {
        return player;
    }

    public MapData getMapData() {
        return mapData;
    }

    public boolean isChangeLevel() {
        return changeLevel;
    }



    public void setChangeLevel(boolean changeLevel) {
        this.changeLevel = changeLevel;
    }


    public void setPlayerData(int score, String name, int avatarId, int hp, int diff) {
        player.setScore(score);
        player.setIdAvatar(avatarId);
        player.setPlayerName(name);
        player.setHpLevel(hp);
        player.setDifficulty(diff);
    }

    public void setScore(int score) {
        player.setScore(score);
    }

    public void checkForCollisions() {
        int currLevel = mapData.getLevel();
        ArrayList<Wall> borderWalls = mapData.getBorderWalls();
        ArrayList<Wall> walls = mapData.getWallsInLevel(currLevel);
        ArrayList<Enemy> enemies = mapData.getEnemies(currLevel);
        ArrayList<PowerUp> powerUps = mapData.getPowerUp(currLevel);
        ArrayList<Weapon> weapons = mapData.getWeapons(currLevel);
        Key key = mapData.getKey();
        System.out.println(key.getX());
        for (Wall wall : walls) {
            if (checkCollision(player, wall)) {
                player.notifyCollision(wall, player.getMoveStrategy());
            }
        }
        for (Wall borderWall : borderWalls) {
            if (checkCollision(player, borderWall)) {
                player.notifyCollision(borderWall, player.getMoveStrategy());
            }
        }
        if (!player.getIsInvincible()) {
            for (Enemy enemy: enemies) {
                if (enemy.getVisible() && (checkCollision(player, enemy)
                        || checkAdjacentCollision(player, enemy))) {
                    player.notifyCollision(enemy, player.getMoveStrategy());
                    player.toggleIsInvincible();
                }
            }
        }

        for (PowerUp powerUp : powerUps) {
            if (powerUp.getVisible() && checkCollision(player, powerUp)) {
                player.notifyCollision(powerUp, player.getMoveStrategy());
            }
        }

        for (Weapon weapon : weapons) {
            if (weapon.getVisible() && checkCollision(player, weapon)) {
                player.notifyCollision(weapon, player.getMoveStrategy());
            }
        }
        if (key.getVisible() && checkCollision(player, key)) {
            player.notifyCollision(key, player.getMoveStrategy());
        }
    }

    public boolean checkCollision(Collidable e1, Collidable e2) {
        if (e1.getX() == e2.getX() && e1.getY() == e2.getY()) {
            return true;
        }
        return false;
    }

    public boolean checkAdjacentCollision(Player player, Enemy enemy) {
        if (player.getX() == enemy.getX() + 1 && player.getY() == enemy.getY()) {
            return true;
        } else if (player.getX() == enemy.getX() - 1 && player.getY() == enemy.getY()) {
            return true;
        } else if (player.getY() == enemy.getY() + 1 && player.getX() == enemy.getX()) {
            return true;
        } else if (player.getY() == enemy.getY() - 1 && player.getX() == enemy.getX()) {
            return true;
        }
        return false;
    }

    public void checkForDoor() {
        changeLevel = false;
        Key key = mapData.getKey();
        if (key.getVisible()) {
            return;
        }
        ArrayList<Door> doors = mapData.getDoorsInLevel(mapData.getLevel());

        for (Door door : doors) {
            if (playerAtDoor(player.getX(), player.getY(), door.getX(), door.getY())) {
                mapData.setLevel(mapData.getLevel() + 1);
                changeLevel = true;
            }
        }
    }

    private boolean playerAtDoor(int playerX, int playerY, int doorX, int doorY) {
        if (playerX == doorX && playerY == doorY) {
            return true;
        }
        return false;
    }

    public ArrayList<Enemy> getEnemiesInLevel() {
        ArrayList<Enemy> enemiesInLevel = mapData.getEnemies(mapData.getLevel());
        return enemiesInLevel;
    }

    public ArrayList<Weapon> getWeaponsInLevel() {
        ArrayList<Weapon> weaponsInLevel = mapData.getWeapons(mapData.getLevel());
        return weaponsInLevel;
    }

    public ArrayList<PowerUp> getPowerUpsInLevel() {
        ArrayList<PowerUp> powerUpsInLevel = mapData.getPowerUp(mapData.getLevel());
        return powerUpsInLevel;
    }

    public Enemy getEnemy1() {
        ArrayList<Enemy> enemiesInLevel =
                mapData.getEnemies(mapData.getLevel());
        Enemy enemy1 = enemiesInLevel.get(0);
        return enemy1;
    }

    public Enemy getEnemy2() {
        ArrayList<Enemy> enemiesInLevel =
                mapData.getEnemies(mapData.getLevel());
        Enemy enemy2 = enemiesInLevel.get(1);
        return enemy2;
    }

}
