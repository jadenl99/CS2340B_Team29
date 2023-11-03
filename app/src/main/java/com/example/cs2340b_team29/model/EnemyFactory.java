package com.example.cs2340b_team29.model;

/**
 * Class responsible for creating the different types of enemies
 */
public class EnemyFactory {
    /**
     * Create an instance of the specified enemy type
     *
     * @param enemyType the type of enemy to create
     * @return the specific enemy
     */
    public Enemy createEnemy(String enemyType) {
        // add parameters as needed as the program grows
        if (enemyType.equalsIgnoreCase("ninja")) {
            return new Ninja();
        } else if (enemyType.equalsIgnoreCase("snake")) {
            return new Snake();
        } else if (enemyType.equalsIgnoreCase("wolf")) {
            return new Wolf();
        } else if (enemyType.equalsIgnoreCase("spider")) {
            return new Spider();
        } else {
            throw new IllegalArgumentException("Invalid enemy type");
        }
    }
}
