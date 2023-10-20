package com.example.cs2340b_team29;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;


public class L1View extends SurfaceView implements Runnable {


    private int screenX;
    private int screenY;
    private Thread thread;
    private boolean isPlaying = false;
    private Paint paint;
    private GameActivity activity;
    private L1Map l1Map;
    public L1View(GameActivity activity, int screenX, int screenY, L1Map l1Map) {
        super(activity);
        this.activity = activity;

        this.screenX = screenX;
        this.screenY = screenY;
        this.l1Map = l1Map;

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
            canvas.drawBitmap(l1Map.getBackground(), l1Map.getX(), l1Map.getY(),
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
