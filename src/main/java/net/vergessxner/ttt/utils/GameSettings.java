package net.vergessxner.ttt.utils;

/**
 * @author Jonas
 * Created: 03.01.2021
 * Class: GameSettings
 */

public class GameSettings {

    private final int maxPlayer;
    private final int minPlayer;


    public GameSettings(int maxPlayer, int minPlayer) {
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public int getMinPlayer() {
        return minPlayer;
    }

}
