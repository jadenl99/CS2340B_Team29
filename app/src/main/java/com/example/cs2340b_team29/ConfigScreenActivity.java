package com.example.cs2340b_team29;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigScreenActivity extends AppCompatActivity {
    Button configToGameBtn, easyBtn, mediumBtn, hardBtn, exitBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        configToGameBtn = findViewById(R.id.configToGameBtn);

        configToGameBtn.setOnClickListener((View v) -> {
            Intent toGameScreen = new Intent(ConfigScreenActivity.this, GameActivity.class);
            startActivity(toGameScreen);
        });

        //TODO: show options for difficulty, sprite, and a textfield for name
        // . Maybe we represent game data in its own class?




    }
}
