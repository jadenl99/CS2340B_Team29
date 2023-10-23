package com.example.cs2340b_team29;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.util.Log;
import android.view.View;

import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveLeft;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveStrategy;
import com.example.cs2340b_team29.viewmodel.MoveUp;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;

/**
 * Responsible for displaying levels and the player avatar on the screen.
 */
public class LevelView extends SurfaceView implements Runnable, View.OnKeyListener {


    private int screenX;
    private int screenY;
    private Thread thread;
    private boolean isPlaying = false;
    private Paint paint;
    private GameActivity activity;
    private Bitmap background;
    private double tileWidth;
    private Rect destinationRect;
    private double tileHeight;
    private final int NUM_TILES_WIDE = 11;
    private final int NUM_TILES_LONG = 23;
    private PlayerViewModel playerViewModel;
    private int currX = 3;
    private int currY = 0;
    public LevelView(GameActivity activity, int screenX, int screenY,
                  Bitmap background) {
        super(activity);
        this.activity = activity;

        this.screenX = screenX;
        this.screenY = screenY;
        this.background = background;
        this.tileWidth = (double) screenX / NUM_TILES_WIDE;
        this.tileHeight = (double) screenY / NUM_TILES_LONG;
        playerViewModel = new PlayerViewModel();
        setOnKeyListener(this);
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
        Log.d("WIDTH", Integer.toString(coords[0]));
        Log.d("HEIGHT", Integer.toString(coords[1]));
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
            // payerViewModel.move(currX , currY);
            currX = playerViewModel.getPlayer().getX();
            currY = playerViewModel.getPlayer().getY();
            System.out.println(currX + " " + currY);
            int[] coords = calcPixelsBasedOnIndices(currX, currY);

           /* if (drawCounter != 0) {
                playerViewModel.move(currX, currY);
                coords = calcPixelsBasedOnIndices(currX, currY);
            } */

            int intTileWidth = (int) tileWidth;
            int intTileHeight = (int) tileHeight;
            Bitmap playerBitMap = playerViewModel.getPlayer().getBitmapAvatar();
            destinationRect = new Rect(coords[0], coords[1], coords[0] + intTileWidth, coords[1]+ intTileHeight);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(playerBitMap, intTileWidth, intTileHeight, false);
            canvas.drawBitmap(resizedBitmap, null, destinationRect, paint);
            drawCounter++;

             // moved sample code to bottom!

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(8000);

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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (keyCode) {
            default:
                System.out.println(super.onKeyDown(keyCode, event));
                return super.onKeyDown(keyCode, event);
            case KeyEvent.KEYCODE_DPAD_LEFT:
                MoveStrategy left = new MoveLeft();
                playerViewModel.setMoveStrategy(left);
                playerViewModel.move(); // Update player position
                invalidate(); // Request a redraw of the view
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                MoveStrategy right = new MoveRight();
                playerViewModel.setMoveStrategy(right);
                playerViewModel.move(); // Update player position
                invalidate(); // Request a redraw of the view
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                MoveStrategy up = new MoveUp();
                playerViewModel.setMoveStrategy(up);
                playerViewModel.move(); // Update player position
                invalidate(); // Request a redraw of the view
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                System.out.println("down arrow clicked");
                MoveStrategy down = new MoveDown();
                playerViewModel.setMoveStrategy(down);
                playerViewModel.move(); // Update player position
                System.out.println("onKey currY: " + currY);
                invalidate(); // Request a redraw of the view
                break;
        };
        return true;
    }
}

/*int[] coords = calcPixelsBasedOnIndices(3, 1);
            int intTileWidth = (int) tileWidth;
            int intTileHeight = (int) tileHeight;
            Bitmap playerBitMap = playerViewModel.getPlayer().getBitmapAvatar();
            destinationRect = new Rect(coords[0], coords[1], coords[0] + intTileWidth, coords[1]+ intTileHeight);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(playerBitMap, intTileWidth, intTileHeight, false);
            canvas.drawBitmap(resizedBitmap, null, destinationRect, paint);
            //canvas.drawBitmap(playerBitMap, coords[0], coords[1], paint);
            coords = calcPixelsBasedOnIndices(8, 10);
            playerBitMap = playerViewModel.getPlayer().getBitmapAvatar();
           // canvas.drawBitmap(playerBitMap, coords[0], coords[1], paint);*/
