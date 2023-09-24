package com.example.cs2340b_team29;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class ConfigScreenActivity extends AppCompatActivity {
    private Button configToGameBtn;
    private ChipGroup difficultyCGroup;
    private TextView nameInput;
    private RadioGroup avatarGroup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        configToGameBtn = findViewById(R.id.configToGameBtn);
        difficultyCGroup = findViewById(R.id.difficultyCGroup);
        nameInput = findViewById(R.id.nameInput);
        avatarGroup = findViewById(R.id.avatarGroup);
        configToGameBtn.setOnClickListener((View v) -> {

            // Accounts for invalid input
            // TODO add warning message
            String playerName = nameInput.getText().toString();
            if (playerName == null || playerName.trim().equals("")) {
                Toast.makeText(getApplicationContext(),
                        "Invalid name", Toast.LENGTH_LONG).show();
                return;
            }
            // Data to pass on to the game screen
            Bundle extras = initializeGameData();

            //Transition to the Game screen
            Intent toGameScreen = new Intent(ConfigScreenActivity.this, GameActivity.class);
            toGameScreen.putExtras(extras);
            startActivity(toGameScreen);
        });




    }

    private Bundle initializeGameData() {
        String playerName = nameInput.getText().toString();
        String difficulty = "";
        //double difficultyMultiplier = 1.0;
        Bundle extras = new Bundle();
        int avatarId = 1;
        for (int i = 0; i < difficultyCGroup.getChildCount(); i++) {
            Chip currChip = (Chip) difficultyCGroup.getChildAt(i);
            String chipText = currChip.getText().toString();
            if (currChip.isChecked()) {
                switch (chipText) {
                case "Easy":
                    //difficultyMultiplier = 0.85;
                    difficulty = "Easy";
                    break;
                case "Medium":
                    //difficultyMultiplier = 1.0;
                    difficulty = "Medium";
                    break;
                default:
                    //
                    //difficultyMultiplier = 1.25;
                    difficulty = "Hard";
                }
            }
        }

        for (int i = 0; i < avatarGroup.getChildCount(); i++) {
            RadioButton avatar = (RadioButton) avatarGroup.getChildAt(i);
            if (avatar.isChecked()) {
                String currAvatar = avatar.getText().toString();
                switch (currAvatar) {
                case "Avatar 1":
                    avatarId = 1;
                    break;
                case "Avatar 2":
                    Log.d("Check", "Avatar 2 selected");
                    avatarId = 2;
                    break;
                default:
                    avatarId = 3;
                }
            }
        }


        extras.putString("PLAYER_NAME", playerName);
        extras.putString("DIFFICULTY", difficulty);
        //extras.putInt("AVATAR_ID", ID);
        return extras;


    }
}
