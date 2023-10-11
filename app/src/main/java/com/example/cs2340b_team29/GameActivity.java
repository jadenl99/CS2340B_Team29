package com.example.cs2340b_team29;



import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    private L1View l1View;
    private L2View l2View;
    private L3View l3View;
    private Point point = new Point();

    private Button level1Button;
    private Button level2Button;
    private Button exitButton;

    private Handler handler = new Handler();

    private PlayerViewModel playerViewModel;

    private Runnable myMethodRunnable = new Runnable() {

        @Override
        public void run() {
            playerViewModel.changeScore(-1);
            handler.postDelayed(this, 1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindowManager().getDefaultDisplay().getSize(point);


        L1Map l1Map = new L1Map(point.x, point.y, getResources());
        l1View = new L1View(this, point.x, point.y, l1Map);

        L2Map l2Map = new L2Map(point.x, point.y, getResources());
        l2View = new L2View(this, point.x, point.y, l2Map);

        L3Map l3Map = new L3Map(point.x, point.y, getResources());
        l3View = new L3View(this, point.x, point.y, l3Map);

        setContentView(l1View);

        /*level1Button.findViewById(R.id.level1);
        level1Button.setOnClickListener((View v) -> {
            setContentView(l1View);
        });

        level2Button.findViewById(R.id.level2);
        level2Button.setOnClickListener((View v) -> {
            setContentView(l2View);
        });

        exitButton.findViewById(R.id.exit);
        exitButton.setOnClickListener((View v) -> {
            Intent goToEndScreen = new Intent(GameActivity.this, EndingActivity.class);
            startActivity(goToEndScreen);
        });*/
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