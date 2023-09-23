package com.example.cs2340b_team29;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;

public class GameActivity extends AppCompatActivity {
    private Button nextButton;
    private TextView playerNameLabel;
    private TextView difficultyLabel;

    private TextView hpLevelLabel;
    private ImageView avatarImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        nextButton = findViewById(R.id.nextButton);
        playerNameLabel = findViewById(R.id.nameLabel);
        difficultyLabel = findViewById(R.id.difficultyLabel);
        hpLevelLabel = findViewById(R.id.hpLevelLabel);
        avatarImage = findViewById(R.id.avatarImage);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String playerName = extras.getString("PLAYER_NAME");
        String difficulty = extras.getString("DIFFICULTY");
        //String hpLevel = extras.getString("HP_LEVEL");

        playerNameLabel.setText(playerName);
        difficultyLabel.setText(difficulty);


    }
}
