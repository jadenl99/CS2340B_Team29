package com.example.cs2340b_team29.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.cs2340b_team29.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    /** public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[3];
        spriteArray[0] = new Sprite(this, new Rect(0*64, 0, 1*64, 64));
        spriteArray[1] = new Sprite(this, new Rect(1*64, 0, 2*64, 64));
        spriteArray[2] = new Sprite(this, new Rect(2*64, 0, 3*64, 64));
    } */

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getGrassSprite() {
        return getSpriteByindex(0, 0);
    }

    private Sprite getSpriteByindex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
            idxCol*SPRITE_WIDTH_PIXELS,
            idxRow*SPRITE_HEIGHT_PIXELS,
            (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
            ));
    }
}
