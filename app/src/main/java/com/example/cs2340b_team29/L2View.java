package com.example.cs2340b_team29;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;


public class L2View extends SurfaceView implements Runnable {

    private int screenX;
    private int screenY;
    private Thread thread;
    private boolean isPlaying = false;
    private Paint paint;
    private GameActivity activity;
    private Bitmap background;
    public L2View(GameActivity activity, int screenX, int screenY,
                  Bitmap background) {
        super(activity);
        this.activity = activity;

        this.screenX = screenX;
        this.screenY = screenY;
        this.background = background;

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
}
