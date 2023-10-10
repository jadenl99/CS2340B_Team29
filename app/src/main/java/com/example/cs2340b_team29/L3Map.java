package com.example.cs2340b_team29;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class L3Map {
    int x = 0;
    int y = 0;
    Bitmap background;

    L3Map(int screenX, int screenY, Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.map3);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}
