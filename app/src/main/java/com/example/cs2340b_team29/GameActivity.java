package com.example.cs2340b_team29;



import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import android.view.View;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340b_team29.collision.EnemyCollisionHandler;
import com.example.cs2340b_team29.collision.WallCollisionHandler;
import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.viewmodel.MapDataViewModel;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveLeft;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;
import com.example.cs2340b_team29.viewmodel.MoveUp;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;

import java.util.ArrayList;

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
    private int playerHP;
    private int avatarChosen;
    private TextView nameLabel;

    private String name;

    private Player player1;
    private int enemy1X;
    private int enemy1Y;
    private int enemy2X;

    private int enemy2Y;

    private MoveStrategy enemy1Move;
    private MoveStrategy enemy2Move;

    private FrameLayout gameContainer;

    private Handler handler = new Handler();

    private PlayerViewModel playerViewModel;


    private Runnable scoreCountDown;
    private Runnable enemy1Movement;
    private Runnable enemy2Movement;
    private WallCollisionHandler wallCollisionHandler;
    private EnemyCollisionHandler enemyCollisionHandler;
    private MapDataViewModel mapDataViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        mapDataViewModel =
                new ViewModelProvider(this).get(MapDataViewModel.class);
        playerViewModel.setScore(1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        MoveStrategy down = new MoveDown();
        MoveStrategy right = new MoveRight();
        playerViewModel.getEnemy1().setMoveStrategy(down);
        playerViewModel.getEnemy2().setMoveStrategy(right);

        setEnemyBitmaps();


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


        //enemy 1 up and down movement logic on a timer
        enemy1Movement = new Runnable() {
            @Override
            public void run() {
                MoveStrategy up = new MoveUp();
                MoveStrategy down = new MoveDown();

                enemy1Y = playerViewModel.getEnemy1().getY();
                playerHP = playerViewModel.getPlayer().getHP();
                enemy1Move = playerViewModel.getEnemy1().getMoveStrategy();

                if (enemy1Y < 22 && enemy1Move instanceof MoveDown) {
                    playerViewModel.getEnemy1().move();
                } else if (enemy1Y == 0 && enemy1Move instanceof MoveUp) {
                    playerViewModel.getEnemy1().setMoveStrategy(down);
                    playerViewModel.getEnemy1().move();
                } else {
                    playerViewModel.getEnemy1().setMoveStrategy(up);
                    playerViewModel.getEnemy1().move();
                }
                hpLevelLabel.setText("HP: " + Integer.toString(playerHP));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(enemy1Movement);

        //enemy 2 left to right movement logic on a timer
        enemy2Movement = new Runnable() {
            @Override
            public void run() {
                MoveStrategy left = new MoveLeft();
                MoveStrategy right = new MoveRight();

                enemy2X = playerViewModel.getEnemy2().getX();
                playerHP = playerViewModel.getPlayer().getHP();
                enemy2Move = playerViewModel.getEnemy2().getMoveStrategy();

                if (enemy2X < 10 && enemy2Move instanceof MoveRight) {
                    playerViewModel.getEnemy2().move();
                } else if (enemy2X == 0 && enemy2Move instanceof MoveLeft) {
                    playerViewModel.getEnemy2().setMoveStrategy(right);
                    playerViewModel.getEnemy2().move();
                } else {
                    playerViewModel.getEnemy2().setMoveStrategy(left);
                    playerViewModel.getEnemy2().move();
                }

                playerViewModel.checkForCollisions();
                hpLevelLabel.setText("HP: " + Integer.toString(playerHP));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(enemy2Movement);

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
        enemyCollisionHandler = new EnemyCollisionHandler();
        playerViewModel.getPlayer().subscribe(wallCollisionHandler);
        playerViewModel.getPlayer().subscribe(enemyCollisionHandler);
        for (Enemy enemy: playerViewModel.getEnemiesInLevel()) {
            enemy.subscribe(wallCollisionHandler);
        }

        // finds gameFrame and initializes to level 1
        gameContainer = findViewById(R.id.gameContainer);
        gameContainer.addView(l1View);
        // request focus on view
        //l1View.setOnKeyListener(l1View);
        l1View.setFocusable(true);
        l1View.setFocusableInTouchMode(true);
        l1View.requestFocus();

        room = mapDataViewModel.getMapData().getLevel();
        //toggleView();

        score = player1.getScore();
        //hpLevel = player1.getHP();
        //avatarChosen = player1.getIdAvatar();
        name = player1.getPlayerName();

        playerScoreLabel.setText("Score: " + Integer.toString(score));
        nameLabel.setText(name);

        playerViewModel.getPlayer().setX(8);
        playerViewModel.getPlayer().setY(22);
        playerViewModel.getEnemiesInLevel().get(0).setX(3);
        playerViewModel.getEnemiesInLevel().get(0).setY(2);
        playerViewModel.getEnemiesInLevel().get(1).setX(0);
        playerViewModel.getEnemiesInLevel().get(1).setY(9);
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
            if (playerViewModel.getPlayer().getHP() == 0) {
                playerViewModel.getPlayer().setScore(0);
                endGame();
            }

        }
        return true;
    }

    private void toggleView() {
        room = mapDataViewModel.getMapData().getLevel();
        if (room == 2) {
            gameContainer.removeView(l1View);
            gameContainer.addView(l2View);
            l1View.clearFocus();
            //l2View.setOnKeyListener(l2View);
            l2View.requestFocus();
            mapDataViewModel.getMapData().setLevel(2);
            playerViewModel.getPlayer().setX(2);
            playerViewModel.getPlayer().setY(22);
            playerViewModel.getEnemiesInLevel().get(0).setX(5);
            playerViewModel.getEnemiesInLevel().get(0).setY(2);
            playerViewModel.getEnemiesInLevel().get(1).setX(0);
            playerViewModel.getEnemiesInLevel().get(1).setY(11);
        } else if (room == 3) {
            gameContainer.removeView(l2View);
            gameContainer.addView(l3View);
            l2View.clearFocus();
            //l3View.setOnKeyListener(l3View);
            l3View.requestFocus();
            mapDataViewModel.getMapData().setLevel(3);
            playerViewModel.getPlayer().setX(7);
            playerViewModel.getPlayer().setY(22);
            playerViewModel.getEnemiesInLevel().get(0).setX(7);
            playerViewModel.getEnemiesInLevel().get(0).setY(2);
            playerViewModel.getEnemiesInLevel().get(1).setX(1);
            playerViewModel.getEnemiesInLevel().get(1).setY(17);
        } else if (room > 3) {
            endGame();
        }
    }

    private void endGame() {
        gameContainer.removeAllViews();
        handler.removeCallbacks(scoreCountDown);
        handler.removeCallbacks(enemy1Movement);
        handler.removeCallbacks(enemy2Movement);
        playerViewModel.getPlayer().unsubscribe(wallCollisionHandler);
        playerViewModel.getPlayer().unsubscribe(enemyCollisionHandler);
        Intent toEndScreen = new Intent(GameActivity.this, EndingActivity.class);
        startActivity(toEndScreen);
    }
    private void setEnemyBitmaps() {
        ArrayList<Enemy> enemies = playerViewModel.getMapData().getAllEnemies();
        for (Enemy enemy: enemies) {
            if (enemy.getEnemyID() == 1) {
                Drawable enemy1 = getDrawable(R.drawable.ninja);
                enemy.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.ninja));
            } else if (enemy.getEnemyID() == 2) {
                Drawable enemy2 = getDrawable(R.drawable.snake);
                enemy.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.snake));
            } else if (enemy.getEnemyID() == 3) {
                Drawable enemy3 = getDrawable(R.drawable.ninja);
                enemy.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.spider));
            } else {
                Drawable enemy4 = getDrawable(R.drawable.wolf);
                enemy.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.wolf));
            }
        }
    }
    /*
    private void setEnemyBitmaps() {
        ArrayList<Enemy> enemies = playerViewModel.getEnemiesInLevel();
        for (Enemy enemy: enemies) {
            if (enemy.getEnemyID() == 1) {
                Drawable enemy1 = getDrawable(R.drawable.ninja);
                enemy.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.ninja));
            } else if (enemy.getEnemyID() == 2) {
                Drawable enemy2 = getDrawable(R.drawable.snake);
                enemy.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.snake));
            } else if (enemy.getEnemyID() == 3) {
                Drawable enemy3 = getDrawable(R.drawable.spider);
                enemy.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.spider));
            } else {
                Drawable enemy4 = getDrawable(R.drawable.wolf);
                enemy.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.wolf));
            }
        }
    }

     */

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

