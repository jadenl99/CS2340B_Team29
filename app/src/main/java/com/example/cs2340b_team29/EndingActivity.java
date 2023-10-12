package com.example.cs2340b_team29;

import androidx.lifecycle.ViewModelProvider;

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
    private int score;
    private String playerName;
    private long currentDateTime;

    private LeaderboardViewModel leaderboardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        leaderboardViewModel = new ViewModelProvider(this).get(LeaderboardViewModel.class);

        firstPlace = findViewById(R.id.firstplace);
        secondPlace = findViewById(R.id.secondplace);
        thirdPlace = findViewById(R.id.thirdplace);
        fourthPlace = findViewById(R.id.fourthplace);
        fifthPlace = findViewById(R.id.fifthplace);
        mostRecentAttempt = findViewById(R.id.mostRecentAttempt);

        //Intent intent = getIntent();
        //Bundle extras = intent.getExtras();
        //create new LeaderboardEntry using last game's information
        //String playerName = extras.getString("PLAYER_NAME");
        //int avatarChosen = extras.getInt("AVATAR_ID");

        Player player1 = Player.getPlayer();
        int hpLevel = player1.getHP();
        int avatarChosen = player1.getIdAvatar();
        playerName = player1.getPlayerName();
        score = player1.getScore();
        currentDateTime = System.currentTimeMillis();
        LeaderboardEntry latestAttempt = new LeaderboardEntry(playerName, score, currentDateTime);


        //add latest player to array
        //leaderboardViewModel.addLatestAttempt(latestAttempt);

//        //sort player history in descending order
//        leaderboardViewModel.sortAttempts();
//
//        //update leaderBoard text
//        if (leaderboard.getAttempts().size() > 0) {
//            firstPlace.setText(leaderboard.getAttempts().get(0).getName() +leaderboard.getAttempts().get(0).getScore() + leaderboard.getAttempts().get(0).getDateTime());
//        }
//        if (leaderboard.getAttempts().size() > 1) {
//            secondPlace.setText(leaderboard.getAttempts().get(1).getName() + leaderboard.getAttempts().get(1).getScore() + leaderboard.getAttempts().get(1).getDateTime());
//        }
//        if (leaderboard.getAttempts().size() > 2) {
//            thirdPlace.setText(leaderboard.getAttempts().get(2).getName() + leaderboard.getAttempts().get(2).getScore() + leaderboard.getAttempts().get(2).getDateTime());
//        }
//        if (leaderboard.getAttempts().size() > 3) {
//            fourthPlace.setText(leaderboard.getAttempts().get(3).getName() + leaderboard.getAttempts().get(3).getScore() + leaderboard.getAttempts().get(3).getDateTime());
//        }
//        if (leaderboard.getAttempts().size() > 4) {
//            fifthPlace.setText(leaderboard.getAttempts().get(4).getName() + leaderboard.getAttempts().get(4).getScore() + leaderboard.getAttempts().get(4).getDateTime());
//        }

        //set most recent attempt text
        //mostRecentAttempt.setText("Most Recent Score: " + score);
//
        restartBtn = findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener((View v) -> {
            Intent goToStartScreen = new Intent(EndingActivity.this,
                    MainActivity.class);
            startActivity(goToStartScreen);
        });
    }

}