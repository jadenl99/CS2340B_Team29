package com.example.cs2340b_team29;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340b_team29.graphics.SpriteSheet;
import com.example.cs2340b_team29.map.Tilemap;


public class GameActivity extends AppCompatActivity {
    private Button nextButton;
    private TextView playerNameLabel;
    private TextView difficultyLabel;

    private TextView hpLevelLabel;
    private ImageView avatarImage;
    private final Tilemap tilemap;
    private SpriteSheet spriteSheet;

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

        if (extras != null) {
            String playerName = extras.getString("PLAYER_NAME");
            String difficulty = extras.getString("DIFFICULTY");
            int avatarChosen = extras.getInt("AVATAR_ID");

            playerNameLabel.setText("Player: " + playerName);
            difficultyLabel.setText("Difficulty: " + difficulty);

            if (difficulty.equals("Easy")) {
                hpLevelLabel.setText("HP: 100");
            } else if (difficulty.equals("Medium")) {
                hpLevelLabel.setText("HP: 50");
            } else if (difficulty.equals("Hard")) {
                hpLevelLabel.setText("HP: 25");
            }

            if (avatarChosen == 1) {
                Drawable avatar1 = getDrawable(R.drawable.avatar1);
                avatarImage.setBackground(avatar1);
            } else if (avatarChosen == 2) {
                Drawable avatar2 = getDrawable(R.drawable.avatar2);
                avatarImage.setBackground(avatar2);
            } else {
                Drawable avatar3 = getDrawable(R.drawable.avatar3);
                avatarImage.setBackground(avatar3);
            }
        }
        
        // Initialize tilemap
        tilemap = new Tilemap(spriteSheet);

        /*
        //this might need to go in a GameView class not sure?
        //to create tilemap
        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            canvas.drawColor(choose a color);
            for (int i = 0; i < tilemap.size(); i++) {
                canvas.drawBitmap(tilemap.get(i).getBm(),tilemap.get(i).getX(),tilemap.get(i).getY(),null);
            }
        }
        */

        //next button leads to end screen
        nextButton.setOnClickListener((View v) -> {
            Intent goToEndScreen = new Intent(GameActivity.this, EndingActivity.class);
            startActivity(goToEndScreen);
        });
    }
}
