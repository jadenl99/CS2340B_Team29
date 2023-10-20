package com.example.cs2340b_team29;



import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
<<<<<<< HEAD
=======
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;
>>>>>>> e055d801bbd02657eecf277be3edbb6d1a4b2b90

public class GameActivity extends AppCompatActivity {

    private L2View l2View;
    private L3View l3View;
    private Point point = new Point();

    private Button level1Button;
    private Button level2Button;
    private Button level3Button;
    private TextView playerScoreLabel;
    private TextView difficultyLabel;

    private TextView hpLevelLabel;
    private ImageView avatarImage;
    private ImageView mapView;
    private static int room;
    private Button exitButton;
    private Button nextButton;

    private int score;
    private int hpLevel;
    private int avatarChosen;
    private TextView nameLabel;

    private String name;

    private Player player1;

    private FrameLayout gameContainer;

    private Handler handler = new Handler();

    private PlayerViewModel playerViewModel;

    private Runnable scoreCountDown;

    // 0 stands for a "wall" (can't pass), 1 is a normal tile, and 2 is a
    // tile that takes you to the next level
    private int[][] l1Array = {
            {0, 1, 2, 2, 2, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private int[][] l2Array = {
            {1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}

    };

    private int[][] l3Array = {
            {1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        playerViewModel.setScore(1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // logic for countdown
        scoreCountDown = new Runnable() {
            @Override
            public void run() {
                playerViewModel.changeScore(-1);
                int currScore = playerViewModel.getPlayer().getScore();
                playerScoreLabel.setText("Score: "
                        + Integer.toString(currScore));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(scoreCountDown);

<<<<<<< HEAD
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

=======
        player1 = Player.getPlayer();

        playerScoreLabel = findViewById(R.id.scoreLabel);
        //difficultyLabel = findViewById(R.id.difficultyLabel);
        hpLevelLabel = findViewById(R.id.hpLevelLabel);
        avatarImage = findViewById(R.id.avatarImage);
        nameLabel = findViewById(R.id.nameLabel);

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener((View v) -> {
            Intent toEndScreen = new Intent(GameActivity.this, EndingActivity.class);
            startActivity(toEndScreen);
        });

        // begin game screen config
        setFullScreenMode();

        // returns size of window to find where gameview should be placed
>>>>>>> e055d801bbd02657eecf277be3edbb6d1a4b2b90
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindowManager().getDefaultDisplay().getSize(point);

<<<<<<< HEAD
        FrameLayout gameViewContainer = (FrameLayout) findViewById(R.id.gameViewContainer);
        private L1Map l1Map = new L1Map(point.x, point.y, getResources());
        private L1View l1View = new L1View(this, point.x, point.y, l1Map);


        gameViewContainer.addView(l1View);
        /*
=======
        // instantiates each game view
        L1Map l1Map = new L1Map(point.x, point.y, getResources());
        l1View = new L1View(this, point.x, point.y, l1Map);

        L2Map l2Map = new L2Map(point.x, point.y, getResources());
        l2View = new L2View(this, point.x, point.y, l2Map);

        L3Map l3Map = new L3Map(point.x, point.y, getResources());
        l3View = new L3View(this, point.x, point.y, l3Map);

        // finds gameFrame and initializes to level 1
        gameContainer = findViewById(R.id.gameContainer);
        gameContainer.addView(l1View);

        room = 1;
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener((View v) -> {
            toggleView();
        });

        score = player1.getScore();
        hpLevel = player1.getHP();
        avatarChosen = player1.getIdAvatar();
        name = player1.getPlayerName();

        playerScoreLabel.setText("Score: " + Integer.toString(score));
        hpLevelLabel.setText("HP: " + Integer.toString(hpLevel));
        nameLabel.setText(name);

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

    private void setFullScreenMode() {
        // Set the system UI visibility flags to enable full-screen mode
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide navigation bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void toggleView() {
        room++;
        System.out.println(room);
        if (room == 2) {
            gameContainer.removeView(l1View);
            gameContainer.addView(l2View);
        } else if (room == 3) {
            gameContainer.removeView(l2View);
            gameContainer.addView(l3View);
        } else if (room > 3) {
            //handler.removeCallbacks(scoreCountDown);
            Intent toEndScreen = new Intent(GameActivity.this, EndingActivity.class);
            startActivity(toEndScreen);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        l1View.pause();
        l2View.pause();
        l3View.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        l1View.resume();
        l2View.resume();
        l3View.resume();
    }
}







    /*
    nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener((View v) -> {
            room++;
            Intent goToConfigScreen = new Intent(MainActivity.this, ConfigScreenActivity.class);
            startActivity(goToConfigScreen);
            (l2View);
    });

        View nextButton = findViewById(R.id.screenButton);
        startBtn.setOnClickListener((View v) -> {
        Intent goToConfigScreen = new Intent(MainActivity.this, ConfigScreenActivity.class);
        startActivity(goToConfigScreen);
    });

        /*
        setFullScreenMode();


        // returns size of window to find where gameview should be placed
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindowManager().getDefaultDisplay().getSize(point);

        // instantiates each game view
>>>>>>> e055d801bbd02657eecf277be3edbb6d1a4b2b90
        L1Map l1Map = new L1Map(point.x, point.y, getResources());
        l1View = new L1View(this, point.x, point.y, l1Map);

        L2Map l2Map = new L2Map(point.x, point.y, getResources());
        l2View = new L2View(this, point.x, point.y, l2Map);

        L3Map l3Map = new L3Map(point.x, point.y, getResources());
        l3View = new L3View(this, point.x, point.y, l3Map);

        // finds gameFrame and initializes to level 1
        gameContainer = findViewById(R.id.gameContainer);
        gameContainer.addView(l1View);

<<<<<<< HEAD
        level1Button.findViewById(R.id.level1);
=======
        // implements button to toggle view
        // TODO each button should switch to its correspodning map
        level1Button = findViewById(R.id.level1Button);
>>>>>>> e055d801bbd02657eecf277be3edbb6d1a4b2b90
        level1Button.setOnClickListener((View v) -> {
            toggleView(l2View);
        });

    }

<<<<<<< HEAD
        exitButton.findViewById(R.id.exit);
        exitButton.setOnClickListener((View v) -> {
            Intent goToEndScreen = new Intent(GameActivity.this, EndingActivity.class);
            startActivity(goToEndScreen);
        });
=======
    private void setFullScreenMode() {
        // Set the system UI visibility flags to enable full-screen mode
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide navigation bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
>>>>>>> e055d801bbd02657eecf277be3edbb6d1a4b2b90
    }
    */

    private void toggleView(SurfaceView level) {
        gameContainer.removeAllViews();
        gameContainer.addView(level);
        //level3Button.bringToFront();
    }

    @Override
    protected void onPause() {
        super.onPause();
        l1View.pause();
        l2View.pause();
        l3View.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        l1View.resume();
        l2View.resume();
        l3View.resume();

    }
    */

