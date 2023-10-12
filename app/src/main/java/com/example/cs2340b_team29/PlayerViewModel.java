package com.example.cs2340b_team29;

import androidx.lifecycle.ViewModel;

public class PlayerViewModel extends ViewModel {
    private Player player;
    public PlayerViewModel() {
        player = Player.getPlayer();

    }

    public void moveLeft() {

    }

    public void moveRight() {

    }

    public void moveUp() {

    }

    public void moveDown() {

    }


    public void changeScore(int score) {
        int newScore = (int) (Math.max(0, score + player.getScore()));
        player.setScore(newScore);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayerData(int score, String name, int avatarId) {
        player.setScore(score);
        player.setIdAvatar(avatarId);
        player.setPlayerName(name);
    }

    public void setScore(int score) {
        player.setScore(score);
    }


}
