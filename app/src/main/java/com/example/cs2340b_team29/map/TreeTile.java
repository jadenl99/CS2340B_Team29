package com.example.cs2340b_team29.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.cs2340b_team29.graphics.Sprite;
import com.example.cs2340b_team29.graphics.SpriteSheet;

public class TreeTile extends Tile {
    private final Sprite sprite;
    public TreeTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getTreeSprite();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
