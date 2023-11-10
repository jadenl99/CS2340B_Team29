package com.example.cs2340b_team29;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.Rect;

import android.view.SurfaceView;

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
    private double tileHeight;
    private final int numTilesWide = 11;
    private final int numTilesLong = 23;
    private PlayerViewModel playerViewModel;

    private int enemy1X;
    private int enemy1Y;

    private int enemy2X;
    private int enemy2Y;
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
            int[] enemy1coords = calcPixelsBasedOnIndices(enemy1X,enemy1Y);

            enemy2X = playerViewModel.getEnemy2().getX();
            enemy2Y = playerViewModel.getEnemy2().getY();
            int[] enemy2coords = calcPixelsBasedOnIndices(enemy2X,enemy2Y);

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
            Bitmap enemy1BitMap = playerViewModel.getEnemiesInLevel().get(0).getBitmap();
            enemy1DestinationRect = new Rect(
                    enemy1coords[0], enemy1coords[1], enemy1coords[0] + intTileWidth, enemy1coords[1]
                    + intTileHeight);
            Bitmap resizedEnemy1Bitmap = Bitmap.createScaledBitmap(enemy1BitMap,
                    intTileWidth, intTileHeight, false);
            canvas.drawBitmap(resizedEnemy1Bitmap, null, enemy1DestinationRect, paint);

            //draw enemy2
            Bitmap enemy2BitMap = playerViewModel.getEnemiesInLevel().get(1).getBitmap();
            enemy2DestinationRect = new Rect(
                    enemy2coords[0], enemy2coords[1], enemy2coords[0] + intTileWidth, enemy2coords[1]
                    + intTileHeight);
            Bitmap resizedEnemy2Bitmap = Bitmap.createScaledBitmap(enemy2BitMap,
                    intTileWidth, intTileHeight, false);
            canvas.drawBitmap(resizedEnemy2Bitmap, null, enemy2DestinationRect, paint);


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

    //    @Override
    //    public boolean onKey(View v, int keyCode, KeyEvent event) {
    //        if (event.getAction() == KeyEvent.ACTION_DOWN) {
    //            switch (keyCode) {
    //                default:
    //                    System.out.println(super.onKeyDown(keyCode, event));
    //                    return super.onKeyDown(keyCode, event);
    //                case KeyEvent.KEYCODE_DPAD_LEFT:
    //                    MoveStrategy left = new MoveLeft();
    //                    playerViewModel.setMoveStrategy(left);
    //                    playerViewModel.move(); // Update player position
    //                    break;
    //                case KeyEvent.KEYCODE_DPAD_RIGHT:
    //                    MoveStrategy right = new MoveRight();
    //                    playerViewModel.setMoveStrategy(right);
    //                    playerViewModel.move(); // Update player position
    //                    break;
    //                case KeyEvent.KEYCODE_DPAD_UP:
    //                    MoveStrategy up = new MoveUp();
    //                    playerViewModel.setMoveStrategy(up);
    //                    playerViewModel.move(); // Update player position
    //                    break;
    //                case KeyEvent.KEYCODE_DPAD_DOWN:
    //                    System.out.println("down arrow clicked");
    //                    MoveStrategy down = new MoveDown();
    //                    playerViewModel.setMoveStrategy(down);
    //                    playerViewModel.move(); // Update player position
    //                    System.out.println("onKey currY: " + currY);
    //                    break;
    //            }
    //            playerViewModel.checkForCollisions();
    //        }
    //        return true;
    //    }
}

/*int[] coords = calcPixelsBasedOnIndices(3, 1);
            int intTileWidth = (int) tileWidth;
            int intTileHeight = (int) tileHeight;
            Bitmap playerBitMap = playerViewModel.getPlayer().getBitmapAvatar();
            destinationRect = new Rect(coords[0], coords[1], coords[0]
            + intTileWidth, coords[1]+ intTileHeight);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(
            , intTileWidth, intTileHeight, false);
            canvas.drawBitmap(resizedBitmap, null, destinationRect, paint);
            //canvas.drawBitmap(playerBitMap, coords[0], coords[1], paint);
            coords = calcPixelsBasedOnIndices(8, 10);
            playerBitMap = playerViewModel.getPlayer().getBitmapAvatar();
           // canvas.drawBitmap(playerBitMap, coords[0], coords[1], paint);*/
