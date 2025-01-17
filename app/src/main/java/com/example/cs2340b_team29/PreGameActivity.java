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
import androidx.lifecycle.ViewModelProvider;

import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.viewmodel.MapDataViewModel;


public class PreGameActivity extends AppCompatActivity {
    private Button testButton;
    private Button nextButton;
    private TextView playerNameLabel;
    private TextView difficultyLabel;

    private TextView hpLevelLabel;
    private ImageView avatarImage;
    private MapDataViewModel mapDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregame);

        Player activePlayer = Player.getPlayer();
        mapDataViewModel = new ViewModelProvider(this).get(MapDataViewModel.class);
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
                mapDataViewModel.setMapData(1);
            } else if (difficulty.equals("Medium")) {
                hpLevelLabel.setText("HP: 50");
                activePlayer.setHpLevel(50);
                mapDataViewModel.setMapData(2);
            } else if (difficulty.equals("Hard")) {
                hpLevelLabel.setText("HP: 25");
                activePlayer.setHpLevel(25);
                mapDataViewModel.setMapData(3);
            }

            if (avatarChosen == 1) {
                Drawable avatar1 = getDrawable(R.drawable.avatar1);
                avatarImage.setBackground(avatar1);
                activePlayer.setIdAvatar(1);
                activePlayer.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar1));
            } else if (avatarChosen == 2) {
                Drawable avatar2 = getDrawable(R.drawable.avatar2);
                avatarImage.setBackground(avatar2);
                activePlayer.setIdAvatar(2);
                activePlayer.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar2));
            } else {
                Drawable avatar3 = getDrawable(R.drawable.avatar3);
                avatarImage.setBackground(avatar3);
                activePlayer.setIdAvatar(2);
                activePlayer.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar3));
            }
            mapDataViewModel.getMapData().setLevel(1);
        }

        //next button leads to game screen
        nextButton.setOnClickListener((View v) -> {
            Intent goToGameScreen = new Intent(PreGameActivity.this, GameActivity.class);
            startActivity(goToGameScreen);
        });
    }

}
