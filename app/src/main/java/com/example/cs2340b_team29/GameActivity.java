package com.example.cs2340b_team29;



import androidx.annotation.ColorInt;
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
import com.example.cs2340b_team29.collision.PlayerAttackCollisionHandler;
import com.example.cs2340b_team29.collision.PowerUpCollisionHandler;
import com.example.cs2340b_team29.collision.WallCollisionHandler;
import com.example.cs2340b_team29.collision.WeaponCollisionHandler;
import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Weapon;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.viewmodel.MapDataViewModel;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveLeft;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;
import com.example.cs2340b_team29.viewmodel.MoveOffScreen;
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
    private PowerUpCollisionHandler powerUpCollisionHandler;
    private WeaponCollisionHandler weaponCollisionHandler;
    private PlayerAttackCollisionHandler playerAttackCollisionHandler;
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
        setWeaponBitmaps();
        setPowerupBitmaps();


        // logic for countdown
        scoreCountDown = new Runnable() {
            @Override
            public void run() {
                playerViewModel.changeScore(-1);
                int currScore = playerViewModel.getPlayer().getScore();
                playerScoreLabel.setText("Score: "
                        + Integer.toString(currScore));
                player1.updateBuffs();
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
                int level = MapData.getMapData().getLevel();
                if (enemy1Y == 16 && level == 2) {
                    playerViewModel.getEnemy1().setMoveStrategy(up);
                    playerViewModel.getEnemy1().move();
                } else if (enemy1Y < 22 && enemy1Move instanceof MoveDown) {
                    playerViewModel.getEnemy1().move();
                } else if (enemy1Y == 0) {
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
                } else if (enemy2X == 0) {
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
        attachPlayerHandlers();

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
        mapDataViewModel.resetMapData();
        playerViewModel.getPlayer().setX(8);
        playerViewModel.getPlayer().setY(22);
        playerViewModel.getEnemiesInLevel().get(0).setX(3);
        playerViewModel.getEnemiesInLevel().get(0).setY(2);
        playerViewModel.getEnemiesInLevel().get(1).setX(0);
        playerViewModel.getEnemiesInLevel().get(1).setY(8);

        Button attackButton = findViewById(R.id.attackButton);
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Enemy enemy: playerViewModel.getEnemiesInLevel()) {
                    if (playerViewModel.checkAdjacentCollision(player1,enemy)) {
                        playerAttackCollisionHandler = new PlayerAttackCollisionHandler();
                        playerViewModel.getPlayer().subscribe(playerAttackCollisionHandler);
                        player1.notifyCollision(enemy,player1.getMoveStrategy());
                        playerViewModel.getPlayer().unsubscribe(playerAttackCollisionHandler);
                    }
                }
            }
        });
    }


    private void attachPlayerHandlers() {
        wallCollisionHandler = new WallCollisionHandler();
        enemyCollisionHandler = new EnemyCollisionHandler();
        powerUpCollisionHandler = new PowerUpCollisionHandler();
        weaponCollisionHandler = new WeaponCollisionHandler();
        playerViewModel.getPlayer().subscribe(powerUpCollisionHandler);
        playerViewModel.getPlayer().subscribe(wallCollisionHandler);
        playerViewModel.getPlayer().subscribe(enemyCollisionHandler);
        playerViewModel.getPlayer().subscribe(weaponCollisionHandler);
    }

    private void detachPlayerHandlers() {
        playerViewModel.getPlayer().unsubscribe(wallCollisionHandler);
        playerViewModel.getPlayer().unsubscribe(enemyCollisionHandler);
        playerViewModel.getPlayer().unsubscribe(powerUpCollisionHandler);
        playerViewModel.getPlayer().unsubscribe(weaponCollisionHandler);
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
            setPlayerWithWeaponBitmaps();
        }
        return true;
    }

    /*@Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                if (event.isShiftPressed()) {
                    player1.setIsAttacking(true);
                }
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    } */

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
            player1.setHasKnife(false);
            player1.setHasSword(false);
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
            player1.setHasKnife(false);
            player1.setHasSword(false);
        } else if (room > 3) {
            endGame();
        }
    }

    private void endGame() {
        gameContainer.removeAllViews();
        handler.removeCallbacks(scoreCountDown);
        handler.removeCallbacks(enemy1Movement);
        handler.removeCallbacks(enemy2Movement);
        detachPlayerHandlers();

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

    private void setWeaponBitmaps() {
        ArrayList<Weapon> weapons = playerViewModel.getMapData().getAllWeapons();
        for (Weapon weapon: weapons) {
            if (weapon.getWeaponId() == 1) {
                Drawable weapon1 = getDrawable(R.drawable.knife);
                weapon.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.knife));
            } else {
                Drawable weapon2 = getDrawable(R.drawable.sword);
                weapon.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.sword));
            }
        }
    }

    private void setPowerupBitmaps() {
        ArrayList<PowerUp> powerups = playerViewModel.getMapData().getAllPowerUps();
        for (PowerUp powerup: powerups) {
            if (powerup.getPowerUpId() == 1) {
                Drawable powerup1 = getDrawable(R.drawable.bomb);
                powerup.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.bomb));
            } else if (powerup.getPowerUpId() == 2) {
                Drawable powerup2 = getDrawable(R.drawable.elixir);
                powerup.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.elixir));
            } else {
                Drawable powerup3 = getDrawable(R.drawable.health);
                powerup.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.health));
            }
        }
    }

    private void setPlayerWithWeaponBitmaps() {
        int playerId = player1.getIdAvatar();
        if (playerId == 1) {
            if (player1.getHasKnife()) {
                Drawable avatar = getDrawable(R.drawable.avatar1knife);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar1knife));
            } else if (player1.getHasSword()) {
                Drawable avatar = getDrawable(R.drawable.avatar1sword);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar1sword));
            } else {
                Drawable avatar = getDrawable(R.drawable.avatar1);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar1));
            }
        } else if (playerId == 2) {
            if (player1.getHasKnife()) {
                Drawable avatar = getDrawable(R.drawable.avatar1knife);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar2knife));
            } else if (player1.getHasSword()) {
                Drawable avatar = getDrawable(R.drawable.avatar2sword);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar2sword));
            } else {
                Drawable avatar = getDrawable(R.drawable.avatar2);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar2));
            }
        } else {
            if (player1.getHasKnife()) {
                Drawable avatar = getDrawable(R.drawable.avatar3knife);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar3knife));
            } else if (player1.getHasSword()) {
                Drawable avatar = getDrawable(R.drawable.avatar3sword);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar3sword));
            } else {
                Drawable avatar = getDrawable(R.drawable.avatar3);
                player1.setBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.avatar3));
            }
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


