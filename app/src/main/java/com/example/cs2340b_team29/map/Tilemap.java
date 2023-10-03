package com.example.cs2340b_team29.map;

import static com.example.cs2340b_team29.map.MapLayout.NUMBER_OF_COL_TILES;
import static com.example.cs2340b_team29.map.MapLayout.NUMBER_OF_ROW_TILES;

import java.util.Map;

public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;

    public Tilemap() {
        mapLayout = new MapLayout();
        initializeTilemap();
    }

    private void initializeTilemap() {
        int[][] layout = mapLayout.getLayout();
        for (int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUMBER_OF_COL_TILES; iCol++) {
                tilemap[iRow][iCol] = Tile.getTile(
                        layout[iRow][iCol]
                        spriteSheet,
                        getRectByIndex(iRow, iCol)
                );
            }
        }
    }

    private Object getRectByIndex(int iRow, int iCol) {
    }

}
