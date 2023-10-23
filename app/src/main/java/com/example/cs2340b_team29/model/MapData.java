package com.example.cs2340b_team29.model;

import java.util.ArrayList;


/**
 * Contains all the map data including the tiling information. MapData is a
 * singleton.
 */
public class MapData {
    private static MapData mapData;
    private ArrayList<Wall> borderWalls;
    private ArrayList<Wall> walls1;
    private ArrayList<Wall> walls2;
    private ArrayList<Wall> walls3;

    private ArrayList<Door> doors1;
    private ArrayList<Door> doors2;
    private ArrayList<Door> doors3;
    private final int MAP_HEIGHT = 23;
    private final int MAP_WIDTH = 11;
    // 0 stands for a "wall" (can't pass), 1 is a normal tile, and 2 is a
    // tile that takes you to the next level
    private final int[][] L1ARRAY = {
            {0, 1, 2, 2, 2, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private final int[][] L2ARRAY = {
            {1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}

    };

    private final int[][] L3ARRAY = {
            {1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},

    };
    private MapData() {
        // pad horizontally
        borderWalls = new ArrayList<>();
        for (int j = 0; j < MAP_WIDTH; j++) {
            borderWalls.add(new Wall(j, -1));
            borderWalls.add(new Wall(j, MAP_HEIGHT));
        }

        // pad vertically
        for (int i = 0; i < MAP_HEIGHT; i++) {
            borderWalls.add(new Wall(-1, i));
            borderWalls.add(new Wall(MAP_WIDTH, i));
        }

        walls1 = new ArrayList<>();
        walls2 = new ArrayList<>();
        walls3 = new ArrayList<>();

        doors1 = new ArrayList<>();
        doors2 = new ArrayList<>();
        doors3 = new ArrayList<>();

        // put walls in each level
        for (int row = 0; row < MAP_HEIGHT; row++) {
            for (int col = 0; col < MAP_WIDTH; col++) {
                if (L1ARRAY[row][col] == 0) {
                    walls1.add(new Wall(col, row));
                }

                if (L2ARRAY[row][col] == 0) {
                    walls2.add(new Wall(col, row));
                }

                if (L3ARRAY[row][col] == 0) {
                    walls3.add(new Wall(col, row));
                }
            }
        }

    // put doors in each level
        for (int row = 0; row < MAP_HEIGHT; row++) {
        for (int col = 0; col < MAP_WIDTH; col++) {
            if (L1ARRAY[row][col] == 2) {
                doors1.add(new Door(col, row));
            }

            if (L2ARRAY[row][col] == 2) {
                doors2.add(new Door(col, row));
            }

            if (L3ARRAY[row][col] == 2) {
                doors3.add(new Door(col, row));
            }
        }
    }
}
    public static synchronized MapData getMapData() {
        if (mapData == null) {
            mapData = new MapData();
        }
        return mapData;
    }

    public ArrayList<Wall> getBorderWalls() {
        return borderWalls;
    }

    public ArrayList<Wall> getWallsInLevel(int level) {
        if (level == 1) {
            return walls1;
        } else if (level == 2) {
            return walls2;
        } else {
            return walls3;
        }
    }

    public ArrayList<Door> getDoorsInLevel(int level) {
        if (level == 1) {
            return doors1;
        } else if (level == 2) {
            return doors2;
        } else {
            return doors3;
        }
    }
}
