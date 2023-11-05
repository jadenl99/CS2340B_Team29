package com.example.cs2340b_team29;



import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import android.view.View;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340b_team29.collision.WallCollisionHandler;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.viewmodel.MapDataViewModel;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveLeft;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;
import com.example.cs2340b_team29.viewmodel.MoveUp;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;

public class GameActivity extends AppCompatActivity {

    private LevelView l1View;
    private LevelView l2View;
    private LevelView l3View;
    private Point point = new Point();

    private Button level1Button;
    private Button level2Button;
    private Button level3Button;
    private TextView playerScoreLabel;
    private TextView difficultyLabel;

    private TextView hpLevelLabel;
    //private ImageView avatarImage;
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
    private WallCollisionHandler wallCollisionHandler;



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

        player1 = Player.getPlayer();

        playerScoreLabel = findViewById(R.id.scoreLabel);
        //difficultyLabel = findViewById(R.id.difficultyLabel);
        hpLevelLabel = findViewById(R.id.hpLevelLabel);
        //avatarImage = findViewById(R.id.avatarImage);
        nameLabel = findViewById(R.id.nameLabel);


        // begin game screen config
        setFullScreenMode();

        // returns size of window to find where gameview should be placed
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindowManager().getDefaultDisplay().getSize(point);

        instantiateGameViews();
        wallCollisionHandler = new WallCollisionHandler();
        playerViewModel.getPlayer().subscribe(wallCollisionHandler);

        // finds gameFrame and initializes to level 1
        gameContainer = findViewById(R.id.gameContainer);
        gameContainer.addView(l1View);
        // request focus on view
        //l1View.setOnKeyListener(l1View);
        l1View.setFocusable(true);
        l1View.setFocusableInTouchMode(true);
        l1View.requestFocus();

        room = player1.getLevel();
        //toggleView();

        score = player1.getScore();
        hpLevel = player1.getHP();
        //avatarChosen = player1.getIdAvatar();
        name = player1.getPlayerName();

        playerScoreLabel.setText("Score: " + Integer.toString(score));
        hpLevelLabel.setText("HP: " + Integer.toString(hpLevel));
        nameLabel.setText(name);

        playerViewModel.getPlayer().setX(8);
        playerViewModel.getPlayer().setY(22);

    }


    private void instantiateGameViews() {
        // instantiates each game view
        Bitmap l1Background = resizeBackground(R.drawable.map1);
        Bitmap l2Background = resizeBackground(R.drawable.map2);
        Bitmap l3Background = resizeBackground(R.drawable.map3);
        l1View = new LevelView(this, point.x, point.y, l1Background);
        l2View = new LevelView(this, point.x, point.y, l2Background);
        l3View = new LevelView(this, point.x, point.y, l3Background);
    }


    private Bitmap resizeBackground(int resID) {
        Bitmap background = BitmapFactory.decodeResource(getResources(), resID);
        return Bitmap.createScaledBitmap(background, point.x, point.y,
                false);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {

            case KeyEvent.KEYCODE_DPAD_LEFT:
                MoveStrategy left = new MoveLeft();
                playerViewModel.getPlayer().setMoveStrategy(left);
                playerViewModel.move(); // Update player position
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                MoveStrategy right = new MoveRight();
                playerViewModel.getPlayer().setMoveStrategy(right);
                playerViewModel.move(); // Update player position
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                MoveStrategy up = new MoveUp();
                playerViewModel.getPlayer().setMoveStrategy(up);
                playerViewModel.move(); // Update player position
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                System.out.println("down arrow clicked");
                MoveStrategy down = new MoveDown();
                playerViewModel.getPlayer().setMoveStrategy(down);
                playerViewModel.move(); // Update player position
                break;
            default:
                System.out.println(super.onKeyDown(keyCode, event));
                return super.onKeyDown(keyCode, event);
            }
            playerViewModel.checkForCollisions();
            playerViewModel.checkForDoor();
            if (playerViewModel.isChangeLevel()) {
                toggleView();
            }

        }
        return true;
    }

    private void toggleView() {
        System.out.println(room);
        room = player1.getLevel();
        if (room == 2) {
            gameContainer.removeView(l1View);
            gameContainer.addView(l2View);
            l1View.clearFocus();
            //l2View.setOnKeyListener(l2View);
            l2View.requestFocus();
            playerViewModel.getPlayer().setLevel(2);
            playerViewModel.getPlayer().setX(2);
            playerViewModel.getPlayer().setY(22);
        } else if (room == 3) {
            gameContainer.removeView(l2View);
            gameContainer.addView(l3View);
            l2View.clearFocus();
            //l3View.setOnKeyListener(l3View);
            l3View.requestFocus();
            playerViewModel.getPlayer().setLevel(3);
            playerViewModel.getPlayer().setX(7);
            playerViewModel.getPlayer().setY(22);
        } else if (room > 3) {
            gameContainer.removeAllViews();
            handler.removeCallbacks(scoreCountDown);
            playerViewModel.getPlayer().unsubscribe(wallCollisionHandler);
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
        // TODO each button should switch to its correspodning map
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

