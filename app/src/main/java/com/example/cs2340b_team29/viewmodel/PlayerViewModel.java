package com.example.cs2340b_team29.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.cs2340b_team29.collision.Collidable;
import com.example.cs2340b_team29.model.Door;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Wall;

import java.util.ArrayList;

public class PlayerViewModel extends ViewModel {
    private Player player;
    private MapData mapData;

    private boolean changeLevel;

    private MoveStrategy moveStrategy;

    public PlayerViewModel() {
        player = Player.getPlayer();
        mapData = MapData.getMapData();

    }

    public void move() {
        moveStrategy.move(player);
    }


    public void changeScore(int score) {
        int newScore = (int) (Math.max(0, score + player.getScore()));
        player.setScore(newScore);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isChangeLevel() {
        return changeLevel;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setChangeLevel(boolean changeLevel) {
        this.changeLevel = changeLevel;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void setPlayerData(int score, String name, int avatarId) {
        player.setScore(score);
        player.setIdAvatar(avatarId);
        player.setPlayerName(name);
    }

    public void setScore(int score) {
        player.setScore(score);
    }

    public void checkForCollisions() {
        ArrayList<Wall> borderWalls = mapData.getBorderWalls();
        ArrayList<Wall> walls = mapData.getWallsInLevel(player.getLevel());

        for (Wall wall : walls) {
            if (checkCollision(player, wall)) {
                player.notifyCollision(wall, moveStrategy);
            }
        }
        for (Wall borderWall : borderWalls) {
            if (checkCollision(player, borderWall)) {
                player.notifyCollision(borderWall, moveStrategy);
            }
        }

    }

    private boolean checkCollision(Collidable e1, Collidable e2) {
        if (e1.getX() == e2.getX() && e1.getY() == e2.getY()) {
            return true;
        }
        return false;
    }

    public void checkForDoor() {
        changeLevel = false;
        ArrayList<Door> doors = mapData.getDoorsInLevel(player.getLevel());

        for (Door door : doors) {
            if (playerAtDoor(player.getX(), player.getY(), door.getX(), door.getY())) {
                player.setLevel(player.getLevel() + 1);
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



}
