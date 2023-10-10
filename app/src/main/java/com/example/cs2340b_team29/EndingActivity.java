package com.example.cs2340b_team29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

public class EndingActivity extends AppCompatActivity {

    private Button restartBtn;
    private ArrayList<Integer> scores;

    private TextView firstPlace;
    private TextView secondPlace;
    private TextView thirdPlace;
    private TextView fourthPlace;
    private TextView fifthPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        firstPlace = findViewById(R.id.firstplace);
        secondPlace = findViewById(R.id.secondplace);
        thirdPlace = findViewById(R.id.thirdplace);
        fourthPlace = findViewById(R.id.fourthplace);
        fifthPlace = findViewById(R.id.fifthplace);

        //add latest score to array
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        scores.add(extras.getInt("SCORE"));

        //sort scores array in descending order
        Collections.sort(scores, Collections.reverseOrder());


        restartBtn = findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener((View v) -> {
            Intent goToStartScreen = new Intent(EndingActivity.this,
                    MainActivity.class);
            startActivity(goToStartScreen);
        });
    }

}
