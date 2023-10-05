package com.example.cs2340b_team29.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private SpriteSheet spriteSheet;
    private Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                rect,
                null
        );
    }

}
