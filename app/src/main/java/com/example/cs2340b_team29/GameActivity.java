package com.example.cs2340b_team29;



import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private L1View l1View;
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
    public static int room;
    private Button exitButton;
    private Button nextButton;

    private int score;
    private int hpLevel;
    private int avatarChosen;
    private TextView nameLabel;

    private String name;

    public Player player1;

    private FrameLayout gameContainer;

    private Handler handler = new Handler();

    private PlayerViewModel playerViewModel;

    private Runnable myMethodRunnable = new Runnable() {

        @Override
        public void run() {
            playerViewModel.changeScore(-1);
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1 = Player.getPlayer();
        room = 1;
        mapView = (ImageView) findViewById(R.id.map);
        mapView.setImageResource(R.drawable.map1);
        nextButton = findViewById(R.id.nextButton);
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

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener((View v) -> {
            room++;
            System.out.println(room);
            if (room == 2) {
                mapView.setImageResource(R.drawable.map2);
            } else if (room == 3) {
                mapView.setImageResource(R.drawable.map3);
            } else if (room > 3) {
                Intent toEndScreen = new Intent(GameActivity.this, EndingActivity.class);
                startActivity(toEndScreen);
            }
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
        L1Map l1Map = new L1Map(point.x, point.y, getResources());
        l1View = new L1View(this, point.x, point.y, l1Map);

        L2Map l2Map = new L2Map(point.x, point.y, getResources());
        l2View = new L2View(this, point.x, point.y, l2Map);

        L3Map l3Map = new L3Map(point.x, point.y, getResources());
        l3View = new L3View(this, point.x, point.y, l3Map);

        // finds gameFrame and initializes to level 1
        gameContainer = findViewById(R.id.gameContainer);
        gameContainer.addView(l1View);

        // implements button to toggle view
        // TODO: each button should switch to its correspodning map
        level1Button = findViewById(R.id.level1Button);
        level1Button.setOnClickListener((View v) -> {
            toggleView(l2View);
        });

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

