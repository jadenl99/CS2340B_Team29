package com.example.cs2340b_team29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndingActivity extends AppCompatActivity {

    private Button restartBtn;
    private Leaderboard leaderboard;
    private TextView firstPlace;
    private TextView secondPlace;
    private TextView thirdPlace;
    private TextView fourthPlace;
    private TextView fifthPlace;
    private TextView mostRecentAttempt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        firstPlace = findViewById(R.id.firstplace);
        secondPlace = findViewById(R.id.secondplace);
        thirdPlace = findViewById(R.id.thirdplace);
        fourthPlace = findViewById(R.id.fourthplace);
        fifthPlace = findViewById(R.id.fifthplace);
        mostRecentAttempt = findViewById(R.id.mostRecentAttempt);

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
        leaderboard.addLatestPlayer(currentPlayer);

        //sort player history in descending order
        leaderboard.sortScores();

        //update leaderBoard text (format this)
        firstPlace.setText(leaderboard.getAttempts().get(0).getPlayerName() +leaderboard.getAttempts().get(0).getScore() + leaderboard.getAttempts().get(0).getTimeDatePlayed());
        secondPlace.setText(leaderboard.getAttempts().get(1).getPlayerName() + leaderboard.getAttempts().get(1).getScore() + leaderboard.getAttempts().get(1).getTimeDatePlayed());
        thirdPlace.setText(leaderboard.getAttempts().get(2).getPlayerName() + leaderboard.getAttempts().get(2).getScore() + leaderboard.getAttempts().get(2).getTimeDatePlayed());
        fourthPlace.setText(leaderboard.getAttempts().get(3).getPlayerName() + leaderboard.getAttempts().get(3).getScore() + leaderboard.getAttempts().get(3).getTimeDatePlayed());
        fifthPlace.setText(leaderboard.getAttempts().get(4).getPlayerName() + leaderboard.getAttempts().get(4).getScore() + leaderboard.getAttempts().get(4).getTimeDatePlayed());

        //set most recent attempt
        mostRecentAttempt.setText("Most Recent Score: " + score);

        restartBtn = findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener((View v) -> {
            Intent goToStartScreen = new Intent(EndingActivity.this,
                    MainActivity.class);
            startActivity(goToStartScreen);
        });
    }

}