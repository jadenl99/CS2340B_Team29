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

    private ArrayList<Enemy> enemies1;
    private ArrayList<Enemy> enemies2;
    private ArrayList<Enemy> enemies3;
    private ArrayList<Enemy> allEnemies;

    private Enemy ninja;
    private Enemy spider;
    private Enemy snake;
    private Enemy wolf;
    private final int mapHeight = 23;
    private final int mapWidth = 11;
    // 0 stands for a "wall" (can't pass), 1 is a normal tile, and 2 is a
    // tile that takes you to the next level
    private final int[][] l1Array = {
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

    private final int[][] l2Array = {
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

    private final int[][] l3Array = {
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

    // 1 is easy, 2 is medium, 3 is hard
    private int difficulty;

    // adjust multipliers in setDifficulty()
    private double difficultyMultiplier;

    private int level;
    private MapData() {
        // pad horizontally
        borderWalls = new ArrayList<>();
        for (int j = 0; j < mapWidth; j++) {
            borderWalls.add(new Wall(j, -1));
            borderWalls.add(new Wall(j, mapHeight));
        }

        // pad vertically
        for (int i = 0; i < mapHeight; i++) {
            borderWalls.add(new Wall(-1, i));
            borderWalls.add(new Wall(mapWidth, i));
        }

        walls1 = new ArrayList<>();
        walls2 = new ArrayList<>();
        walls3 = new ArrayList<>();

        doors1 = new ArrayList<>();
        doors2 = new ArrayList<>();
        doors3 = new ArrayList<>();

        enemies1 = new ArrayList<>();
        enemies2 = new ArrayList<>();
        enemies3 = new ArrayList<>();

        allEnemies = new ArrayList<>();

        wolf = EnemyFactory.createEnemy("wolf");
        ninja = EnemyFactory.createEnemy("ninja");
        snake = EnemyFactory.createEnemy("snake");
        spider = EnemyFactory.createEnemy("spider");

        // put walls in each level
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                if (l1Array[row][col] == 0) {
                    walls1.add(new Wall(col, row));
                }

                if (l2Array[row][col] == 0) {
                    walls2.add(new Wall(col, row));
                }

                if (l3Array[row][col] == 0) {
                    walls3.add(new Wall(col, row));
                }
            }
        }

        // put doors in each level
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                if (l1Array[row][col] == 2) {
                    doors1.add(new Door(col, row));
                }

                if (l2Array[row][col] == 2) {
                    doors2.add(new Door(col, row));
                }

                if (l3Array[row][col] == 2) {
                    doors3.add(new Door(col, row));
                }
            }
        }

        //put enemies in each level
        enemies1.add(spider);
        enemies1.add(snake);

        enemies2.add(ninja);
        enemies2.add(snake);

        enemies3.add(wolf);
        enemies3.add(ninja);

        allEnemies.add(spider);
        allEnemies.add(ninja);
        allEnemies.add(wolf);
        allEnemies.add(snake);

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


    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;


    }

    public int getDifficulty() {
        return difficulty;
    }

    public double getDifficultyMultiplier() {
        return difficultyMultiplier;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public ArrayList<Enemy> getEnemies(int level) {
        if (level == 1) {
            return enemies1;
        } else if (level == 2) {
            return enemies2;
        } else {
            return enemies3;
        }
    }

    public ArrayList<Enemy> getAllEnemies() {
        return allEnemies;

    }
}
