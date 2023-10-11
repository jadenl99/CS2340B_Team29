package com.example.cs2340b_team29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class GameActivity extends AppCompatActivity {

    private L2View l2View;
    private L3View l3View;
    private Point point = new Point();

    private Button level1Button;
    private Button level2Button;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        View nextButton = findViewById(R.id.nextButton);
        View playerNameLabel = findViewById(R.id.nameLabel);
        View difficultyLabel = findViewById(R.id.difficultyLabel);
        View hpLevelLabel = findViewById(R.id.hpLevelLabel);
        View avatarImage = findViewById(R.id.avatarImage);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String playerName = extras.getString("PLAYER_NAME");
            String difficulty = extras.getString("DIFFICULTY");
            int avatarChosen = extras.getInt("AVATAR_ID");

            playerNameLabel.setTextDirection(Integer.parseInt("Player: " + playerName));
            difficultyLabel.setTextDirection(Integer.parseInt("Difficulty: " + difficulty));

            if (difficulty.equals("Easy")) {
                hpLevelLabel.setTextDirection(Integer.parseInt("HP: 100"));
            } else if (difficulty.equals("Medium")) {
                hpLevelLabel.setTextDirection(Integer.parseInt("HP: 50"));
            } else if (difficulty.equals("Hard")) {
                hpLevelLabel.setTextDirection(Integer.parseInt("HP: 25"));
            }

            if (avatarChosen == 1) {
                Drawable avatar1 = getDrawable(R.drawable.avatar1);
                avatarImage.setBackground(avatar1);
            } else if (avatarChosen == 2) {
                Drawable avatar2 = getDrawable(R.drawable.avatar2);
                avatarImage.setBackground(avatar2);
            } else {
                Drawable avatar3 = getDrawable(R.drawable.avatar3);
                avatarImage.setBackground(avatar3);
            }
        }
    }
        
        // Initialize tilemap

        /*
        //this might need to go in a GameView class not sure?
        //to create tilemap
        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            canvas.drawColor(choose a color);
            for (int i = 0; i < tilemap.size(); i++) {
                canvas.drawBitmap(tilemap.get(i).getBm(),tilemap.get(i).getX(),tilemap.get(i).getY(),null);
            }
        }
        */

        /*next button leads to end screen
        nextButton.setOnClickListener((View v) -> { */

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindowManager().getDefaultDisplay().getSize(point);

        FrameLayout gameViewContainer = (FrameLayout) findViewById(R.id.gameViewContainer);
        private L1Map l1Map = new L1Map(point.x, point.y, getResources());
        private L1View l1View = new L1View(this, point.x, point.y, l1Map);


        gameViewContainer.addView(l1View);
        /*
        L1Map l1Map = new L1Map(point.x, point.y, getResources());
        l1View = new L1View(this, point.x, point.y, l1Map);

        L2Map l2Map = new L2Map(point.x, point.y, getResources());
        l2View = new L2View(this, point.x, point.y, l2Map);

        L3Map l3Map = new L3Map(point.x, point.y, getResources());
        l3View = new L3View(this, point.x, point.y, l3Map);

        setContentView(l1View);

        level1Button.findViewById(R.id.level1);
        level1Button.setOnClickListener((View v) -> {
            setContentView(l1View);
        });

        level2Button.findViewById(R.id.level2);
        level2Button.setOnClickListener((View v) -> {
            setContentView(l2View);
        });

        exitButton.findViewById(R.id.exit);
        exitButton.setOnClickListener((View v) -> {
            Intent goToEndScreen = new Intent(GameActivity.this, EndingActivity.class);
            startActivity(goToEndScreen);
        });
    }
    */


    @Override
    protected void onPause() {
        super.onPause();
        l1View.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        l1View.resume();
    }
}