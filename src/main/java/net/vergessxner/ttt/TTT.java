package net.vergessxner.ttt;

import org.bukkit.plugin.java.JavaPlugin;

public final class TTT extends JavaPlugin {

    public static String PREFIX = "";
    private static TTT instance;
    

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TTT getInstance() {
        return instance;
    }
}
