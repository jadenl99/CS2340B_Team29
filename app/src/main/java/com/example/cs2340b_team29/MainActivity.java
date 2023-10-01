package com.example.cs2340b_team29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = findViewById(R.id.startButton);

        // handler to change from start to config screen
        startBtn.setOnClickListener((View v) -> {
            Intent goToConfigScreen = new Intent(MainActivity.this, ConfigScreenActivity.class);
            startActivity(goToConfigScreen);
        });

        Button exitBtn = findViewById(R.id.exitButton);

        // handler to change from exit to end screen
        exitBtn.setOnClickListener((View v) -> {
            Intent goToEndScreen = new Intent(MainActivity.this, EndingActivity.class);
            startActivity(goToEndScreen);
        });

        TextView title = findViewById(R.id.title);
    }


}
