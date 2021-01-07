package net.vergessxner.ttt;

import net.vergessxner.ttt.utils.GameSettings;
import net.vergessxner.ttt.utils.file.ConfigLoader;
import net.vergessxner.ttt.utils.states.provider.GameStateProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class TTT extends JavaPlugin {

    public static String PREFIX = "";
    private static TTT instance;
    private GameSettings gameSettings;

    private GameStateProvider gameStateProvider;


    @Override
    public void onLoad() {
        gameStateProvider = new GameStateProvider();
    }

    @Override
    public void onEnable() {
        instance = this;

        gameSettings = new GameSettings(getConfig().getInt("maxPlayers"), getConfig().getInt("minPlayers"));
        gameStateProvider.startGameState(GameStateProvider.LOBBY);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TTT getInstance() {
        return instance;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }
}
