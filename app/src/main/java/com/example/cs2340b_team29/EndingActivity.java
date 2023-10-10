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
    private ArrayList<Player> players;

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

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //create new player using last game's information
        Player currentPlayer = Player.getPlayer();
        String playerName = extras.getString("PLAYER_NAME");
        int avatarChosen = extras.getInt("AVATAR_ID");
        int score = extras.getInt("SCORE");
        currentPlayer.setPlayerName(playerName);
        currentPlayer.setIdAvatar(avatarChosen);
        currentPlayer.setScore(score);
        long currentDateTime = System.currentTimeMillis();
        currentPlayer.setTimeDatePlayed(currentDateTime);

        //add latest player to array
        players.add(currentPlayer);

        //sort scores array in descending order
        Collections.sort(players, Collections.reverseOrder());//how to sort based on each player's score attribute?

        //update leaderBoard text
        //format this correctly
        firstPlace.setText(players.get(0).getPlayerName() + players.get(0).getScore() + players.get(0).getTimeDatePlayed());
        secondPlace.setText(players.get(1).getPlayerName() + players.get(1).getScore() + players.get(1).getTimeDatePlayed());
        thirdPlace.setText(players.get(2).getPlayerName() + players.get(2).getScore() + players.get(2).getTimeDatePlayed());
        fourthPlace.setText(players.get(3).getPlayerName() + players.get(3).getScore() + players.get(3).getTimeDatePlayed());
        fifthPlace.setText(players.get(4).getPlayerName() + players.get(4).getScore() + players.get(4).getTimeDatePlayed());

        restartBtn = findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener((View v) -> {
            Intent goToStartScreen = new Intent(EndingActivity.this,
                    MainActivity.class);
            startActivity(goToStartScreen);
        });
    }

}
