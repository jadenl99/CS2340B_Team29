package com.example.cs2340b_team29;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340b_team29.model.Player;


public class PreGameActivity extends AppCompatActivity {
    private Button testButton;
    private Button nextButton;
    private TextView playerNameLabel;
    private TextView difficultyLabel;

    private TextView hpLevelLabel;
    private ImageView avatarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregame);

        Player activePlayer = Player.getPlayer();

        nextButton = findViewById(R.id.nextButton);
        playerNameLabel = findViewById(R.id.nameLabel);
        difficultyLabel = findViewById(R.id.difficultyLabel);
        hpLevelLabel = findViewById(R.id.hpLevelLabel);
        avatarImage = findViewById(R.id.avatarImage);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String playerName = extras.getString("PLAYER_NAME");
            String difficulty = extras.getString("DIFFICULTY");
            int avatarChosen = extras.getInt("AVATAR_ID");

            playerNameLabel.setText("Player: " + playerName);
            activePlayer.setPlayerName(playerName);
            difficultyLabel.setText("Difficulty: " + difficulty);

            if (difficulty.equals("Easy")) {
                hpLevelLabel.setText("HP: 100");
                activePlayer.setHpLevel(100);
            } else if (difficulty.equals("Medium")) {
                hpLevelLabel.setText("HP: 50");
                activePlayer.setHpLevel(50);
            } else if (difficulty.equals("Hard")) {
                hpLevelLabel.setText("HP: 25");
                activePlayer.setHpLevel(25);
            }

            if (avatarChosen == 1) {
                Drawable avatar1 = getDrawable(R.drawable.avatar1);
                avatarImage.setBackground(avatar1);
                activePlayer.setIdAvatar(1);
                activePlayer.setBitmapAvatar(BitmapFactory.decodeResource(getResources(), R.drawable.avatar1));
            } else if (avatarChosen == 2) {
                Drawable avatar2 = getDrawable(R.drawable.avatar2);
                avatarImage.setBackground(avatar2);
                activePlayer.setIdAvatar(2);
                activePlayer.setBitmapAvatar(BitmapFactory.decodeResource(getResources(), R.drawable.avatar2));
            } else {
                Drawable avatar3 = getDrawable(R.drawable.avatar3);
                avatarImage.setBackground(avatar3);
                activePlayer.setIdAvatar(2);
                activePlayer.setBitmapAvatar(BitmapFactory.decodeResource(getResources(), R.drawable.avatar3));
            }
            activePlayer.setLevel(1);
        }

        //next button leads to game screen
        nextButton.setOnClickListener((View v) -> {
            Intent goToGameScreen = new Intent(PreGameActivity.this, GameActivity.class);
            startActivity(goToGameScreen);
        });
    }
}
