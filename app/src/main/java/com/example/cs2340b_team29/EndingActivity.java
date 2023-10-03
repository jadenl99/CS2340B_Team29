package com.example.cs2340b_team29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndingActivity extends AppCompatActivity {

    private Button restartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        restartBtn = findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener((View v) -> {
            Intent goToStartScreen = new Intent(EndingActivity.this,
                    MainActivity.class);
            startActivity(goToStartScreen);
        });
    }

}
