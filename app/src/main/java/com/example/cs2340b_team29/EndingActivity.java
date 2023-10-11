package com.example.cs2340b_team29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EndingActivity extends AppCompatActivity {

    private Button restartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

//        TextView scoreTextView = findViewById(R.id.scoreTextView);
//        long completionTime = 0 /* Obtain the player's completion time in milliseconds */;
//        int score = calculateScore(completionTime);
//        scoreTextView.setText("Score: " + score);

        restartBtn = findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener((View v) -> {
            Intent goToStartScreen = new Intent(EndingActivity.this,
                    MainActivity.class);
            startActivity(goToStartScreen);
        });
    }
//    public int calculateScore(long completionTime) {
//        final int MAX_SCORE = 1000;  // The maximum score a player can achieve.
//        final int TIME_THRESHOLD = 60000;  // Time threshold in milliseconds (1 minute).
//
//        // Calculate the score based on completionTime.
//        if (completionTime <= TIME_THRESHOLD) {
//            // The player completed the game in less than 1 minute.
//            int score = (int) (MAX_SCORE * (1.0 - (completionTime / (double) TIME_THRESHOLD)));
//            return score;
//        } else {
//            // The player took longer than 1 minute.
//            return 0;  // You can change this to assign some points for longer times.
//        }
    }




