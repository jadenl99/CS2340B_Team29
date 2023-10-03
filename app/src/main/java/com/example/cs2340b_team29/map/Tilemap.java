package com.example.cs2340b_team29.map;

import static com.example.cs2340b_team29.map.MapLayout.NUMBER_OF_COL_TILES;
import static com.example.cs2340b_team29.map.MapLayout.NUMBER_OF_ROW_TILES;
import static com.example.cs2340b_team29.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.cs2340b_team29.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.cs2340b_team29.graphics.SpriteSheet;

import java.util.Map;

public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public Tilemap(SpriteSheet spriteSheet) {
        mapLayout = new MapLayout();
        this.spriteSheet = spriteSheet;
        initializeTilemap();
    }

    private void initializeTilemap() {
        int[][] layout = mapLayout.getLayout();
        for (int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUMBER_OF_COL_TILES; iCol++) {
                tilemap[iRow][iCol] = Tile.getTile(
                        layout[iRow][iCol],
                        spriteSheet,
                        getRectByIndex(iRow, iCol)
                );
            }
        }
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                NUMBER_OF_COL_TILES*TILE_WIDTH_PIXELS,
                NUMBER_OF_ROW_TILES*TILE_HEIGHT_PIXELS,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUMBER_OF_COL_TILES; iCol++) {
                tilemap[iRow][iCol].draw(mapCanvas);
            }
        }
    }

    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                idxCol*TILE_WIDTH_PIXELS,
                idxRow*TILE_HEIGHT_PIXELS,
                (idxCol + 1)*TILE_WIDTH_PIXELS,
                (idxRow + 1)*TILE_HEIGHT_PIXELS
        );
    }

}
