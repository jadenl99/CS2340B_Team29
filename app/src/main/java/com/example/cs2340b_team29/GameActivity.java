package com.example.cs2340b_team29;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    private L1View l1View;
    private Point point = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindowManager().getDefaultDisplay().getSize(point);


        L1Map l1Map = new L1Map(point.x, point.y, getResources());
        l1View = new L1View(this, point.x, point.y, l1Map);

        setContentView(l1View);
    }


    @Override
    protected void onPause() {
        super.onPause();
        l1View.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        l1View.resume();
    }
}