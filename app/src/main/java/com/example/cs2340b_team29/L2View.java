package com.example.cs2340b_team29;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;


public class L2View extends SurfaceView implements Runnable{

    private int screenX, screenY;
    private Thread thread;
    private boolean isPlaying = false;
    private Paint paint;
    private GameActivity activity;
    private L2Map l2Map;
    public L2View(GameActivity activity, int screenX, int screenY, L2Map l2Map) {
        super(activity);
        this.activity = activity;

        this.screenX = screenX;
        this.screenY = screenY;
        this.l2Map = l2Map;

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
            canvas.drawBitmap(l2Map.background, l2Map.x, l2Map.y, paint);
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

    public void resume () {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause () {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
