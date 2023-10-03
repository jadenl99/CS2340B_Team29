package com.example.cs2340b_team29.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.cs2340b_team29.graphics.SpriteSheet;

public abstract class Tile {

    protected final Rect mapLocationRect;
    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        GRASS_TILE,
        TREE_TILE,
        BUSH_TILE
    }
    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch(TileType.values()[idxTileType]) {
            case GRASS_TILE:
                return new GrassTile(spriteSheet, mapLocationRect);
            case TREE_TILE:
                return new TreeTile(spriteSheet, mapLocationRect);
            case BUSH_TILE:
                return new BushTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }
    }
    public abstract void draw(Canvas canvas);
}
