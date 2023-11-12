package com.example.cs2340b_team29;

import androidx.lifecycle.ViewModelProvider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.cs2340b_team29.model.Leaderboard;
import com.example.cs2340b_team29.model.LeaderboardEntry;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.viewmodel.LeaderboardViewModel;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private Date currentDateTime;

    private LeaderboardViewModel leaderboardViewModel;
    private PlayerViewModel playerViewModel;

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
        playerViewModel =
                new ViewModelProvider(this).get(PlayerViewModel.class);
        Player player1 = playerViewModel.getPlayer();
        int hpLevel = player1.getHP();
        if (hpLevel == 0) {
            TextView status = findViewById(R.id.youWon);
            status.setText("YOU LOST");
        }
        int avatarChosen = player1.getIdAvatar();
        playerName = player1.getPlayerName();
        score = player1.getScore();
        Calendar calendar = Calendar.getInstance();
        currentDateTime = calendar.getTime();
        LeaderboardEntry latestAttempt = new LeaderboardEntry(playerName, score, currentDateTime);


        // update leaderboard
        leaderboardViewModel.addLatestAttempt(latestAttempt);
        leaderboardViewModel.sortAttempts();

        displayLeaderboard();


        // set most recent attempt text
        mostRecentAttempt.setText("Score:" + score + "\n" + "Name: "
                + playerName + "\nDate/Time: " + currentDateTime);

        restartBtn = findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener((View v) -> {

            Intent goToStartScreen = new Intent(EndingActivity.this,
                    MainActivity.class);
            startActivity(goToStartScreen);
        });
    }

    private void displayLeaderboard() {
        int entryListSize =
                leaderboardViewModel.getLeaderboard().getAttempts().size();
        Leaderboard currLeaderboard = leaderboardViewModel.getLeaderboard();
        LeaderboardEntry attempt;
        if (entryListSize > 0) {
            attempt = currLeaderboard.getAttempts().get(0);
            firstPlace.setText(formatEntry(attempt));
        }
        if (entryListSize > 1) {
            attempt = currLeaderboard.getAttempts().get(1);
            secondPlace.setText(formatEntry(attempt));
        }
        if (entryListSize > 2) {
            attempt = currLeaderboard.getAttempts().get(2);
            thirdPlace.setText(formatEntry(attempt));
        }
        if (entryListSize > 3) {
            attempt = currLeaderboard.getAttempts().get(3);
            fourthPlace.setText(formatEntry(attempt));
        }
        if (entryListSize > 4) {
            attempt = currLeaderboard.getAttempts().get(4);
            fifthPlace.setText(formatEntry(attempt));
        }
    }

    private String formatEntry(LeaderboardEntry attempt) {
        Date dateTime = attempt.getDateTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        String formattedDate = dateFormat.format(dateTime);

        return attempt.getName() + " " + attempt.getScore() + " " + formattedDate;
    }

}