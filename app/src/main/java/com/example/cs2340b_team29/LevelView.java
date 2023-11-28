package com.example.cs2340b_team29;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.Rect;

import android.view.SurfaceView;

import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.Key;
import com.example.cs2340b_team29.model.Weapon;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;

/**
 * Responsible for displaying levels and the player avatar on the screen.
 */
public class LevelView extends SurfaceView implements Runnable {


    private int screenX;
    private int screenY;
    private Thread thread;
    private boolean isPlaying = false;
    private Paint paint;
    private GameActivity activity;
    private Bitmap background;
    private double tileWidth;
    private Rect destinationRect;
    private Rect enemy1DestinationRect;
    private Rect enemy2DestinationRect;
    private Rect weaponDestinationRect;
    private Rect powerupDestinationRect;
    private Rect keyDestinationRect;

    private double tileHeight;
    private final int numTilesWide = 11;
    private final int numTilesLong = 23;
    private PlayerViewModel playerViewModel;

    private int enemy1X;
    private int enemy1Y;

    private int enemy2X;
    private int enemy2Y;
    private int weaponX;
    private int weaponY;
    private int powerupX;
    private int powerupY;
    private int currX = 3;
    private int currY = 0;

    public LevelView(GameActivity activity, int screenX, int screenY,
                     Bitmap background) {
        super(activity);
        this.activity = activity;

        this.screenX = screenX;
        this.screenY = screenY;
        this.background = background;
        this.tileWidth = (double) screenX / numTilesWide;
        this.tileHeight = (double) screenY / numTilesLong;
        playerViewModel = new PlayerViewModel();
        //setOnKeyListener(this);
    }


    /**
     * Given x and y in terms of the tiles, outputs the ordered pair of the
     * top-left point of the tile in pixels.
     *
     * @param x x-value (col)
     * @param y y-value (row)
     * @return an int array where coords[0] = x val and coords[1] = y val
     * expressed in terms of pixels
     */

    private int[] calcPixelsBasedOnIndices(int x, int y) {
        int[] coords = new int[2];
        coords[0] = (int) ((x) * tileWidth);
        coords[1] = (int) ((y) * tileHeight);
        return coords;
    }

    @Override
    public void run() {
        isPlaying = true;
        while (isPlaying) {
            draw();
            sleep();
        }
    }


    private void draw() {
        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background, 0, 0,
                    paint);

            // TODO fix size of bitmap here and maybe make the playerBitMap
            //  an instance? Eventually draw() will be called every keypress.
            //  Take advantage of calcPixelsBasedOnIndices()
            // Just an example placing the top left part of the avatar on the
            // top left of map[8][10] (8 tiles down and 10 tiles right, of
            // course in 0-indexed terms).
            // playerViewModel.move(currX , currY);
            currX = playerViewModel.getPlayer().getX();
            currY = playerViewModel.getPlayer().getY();
            int[] coords = calcPixelsBasedOnIndices(currX, currY);

            enemy1X = playerViewModel.getEnemiesInLevel().get(0).getX();
            enemy1Y = playerViewModel.getEnemiesInLevel().get(0).getY();
            int[] enemy1coords = calcPixelsBasedOnIndices(enemy1X, enemy1Y);

            enemy2X = playerViewModel.getEnemy2().getX();
            enemy2Y = playerViewModel.getEnemy2().getY();
            int[] enemy2coords = calcPixelsBasedOnIndices(enemy2X, enemy2Y);

            weaponX = playerViewModel.getWeaponsInLevel().get(0).getX();
            weaponY = playerViewModel.getWeaponsInLevel().get(0).getY();
            int[] weaponCoords = calcPixelsBasedOnIndices(weaponX, weaponY);

            powerupX = playerViewModel.getPowerUpsInLevel().get(0).getX();
            powerupY = playerViewModel.getPowerUpsInLevel().get(0).getY();
            int[] powerupCoords = calcPixelsBasedOnIndices(powerupX, powerupY);

            int intTileWidth = (int) tileWidth;
            int intTileHeight = (int) tileHeight;

            //draw player
            Bitmap playerBitMap = playerViewModel.getPlayer().getBitmap();
            destinationRect = new Rect(
                    coords[0], coords[1], coords[0] + intTileWidth, coords[1]
                    + intTileHeight);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(playerBitMap,
                    intTileWidth, intTileHeight, false);
            canvas.drawBitmap(resizedBitmap, null, destinationRect, paint);

            //draw enemy1
            Enemy enemy1 = playerViewModel.getEnemiesInLevel().get(0);
            if (enemy1.getVisible()) {
                Bitmap enemy1BitMap = enemy1.getBitmap();
                enemy1DestinationRect = new Rect(enemy1coords[0], enemy1coords[1],
                        enemy1coords[0] + intTileWidth, enemy1coords[1]
                        + intTileHeight);
                Bitmap resizedEnemy1Bitmap = Bitmap.createScaledBitmap(enemy1BitMap,
                        intTileWidth, intTileHeight, false);
                canvas.drawBitmap(resizedEnemy1Bitmap, null, enemy1DestinationRect, paint);
            }

            Enemy enemy2 = playerViewModel.getEnemiesInLevel().get(1);
            if (enemy2.getVisible()) {
                Bitmap enemy2BitMap = enemy2.getBitmap();
                enemy2DestinationRect = new Rect(
                        enemy2coords[0], enemy2coords[1],
                        enemy2coords[0] + intTileWidth,
                        enemy2coords[1] + intTileHeight);
                Bitmap resizedEnemy2Bitmap = Bitmap.createScaledBitmap(enemy2BitMap,
                        intTileWidth, intTileHeight, false);
                canvas.drawBitmap(resizedEnemy2Bitmap, null, enemy2DestinationRect, paint);
            }

            //draw weapon
            Weapon weapon = playerViewModel.getWeaponsInLevel().get(0);
            if (weapon.getVisible()) {
                Bitmap weaponBitMap = weapon.getBitmap();
                weaponDestinationRect = new Rect(
                        weaponCoords[0], weaponCoords[1],
                        weaponCoords[0] + intTileWidth,
                        weaponCoords[1] + intTileHeight);
                Bitmap resizedWeaponBitmap = Bitmap.createScaledBitmap(weaponBitMap,
                        intTileWidth, intTileHeight, false);
                canvas.drawBitmap(weaponBitMap, null, weaponDestinationRect, paint);
            }
            //draw powerup
            PowerUp powerUp = playerViewModel.getPowerUpsInLevel().get(0);
            if (powerUp.getVisible()) {
                Bitmap powerupBitMap = powerUp.getBitmap();
                powerupDestinationRect = new Rect(
                        powerupCoords[0], powerupCoords[1],
                        powerupCoords[0] + intTileWidth,
                        powerupCoords[1] + intTileHeight);
                Bitmap resizedPowerupBitmap = Bitmap.createScaledBitmap(powerupBitMap,
                        intTileWidth, intTileHeight, false);
                canvas.drawBitmap(powerupBitMap, null, powerupDestinationRect, paint);
            }

            drawKey(canvas);


            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5);

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void drawKey(Canvas canvas) {
        Key key = playerViewModel.getMapData().getKey();
        if (key.getVisible()) {
            Bitmap keyBitmap = key.getBitmap();
            int[] coords = calcPixelsBasedOnIndices(key.getX(), key.getY());
            keyDestinationRect = new Rect(
                    coords[0], coords[1],
                    coords[0] + (int) tileWidth,
                    coords[1] + (int) tileHeight);
            canvas.drawBitmap(keyBitmap, null, keyDestinationRect, paint);
        }
    }

}
