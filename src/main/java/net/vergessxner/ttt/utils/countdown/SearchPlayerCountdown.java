package net.vergessxner.ttt.utils.countdown;

import net.vergessxner.ttt.TTT;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Jonas
 * Created: 03.01.2021
 * Class: SearchPlayerCountdown
 */

public class SearchPlayerCountdown extends TTTCountdown {

    private BukkitTask task;

    public SearchPlayerCountdown(int maxTime) {
        super(maxTime);
    }

    @Override
    public void startCountdown() {
        setRunning(true);
        if(!getStartFunctions().isEmpty())
            getStartFunctions().forEach(Runnable::run);
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if(!(Bukkit.getOnlinePlayers().size() >= TTT.getInstance().getGameSettings().getMinPlayer()))
                    Bukkit.broadcastMessage(TTT.PREFIX + "§7Es werden noch §c" + (TTT.getInstance().getGameSettings().getMinPlayer() - Bukkit.getOnlinePlayers().size()) + " Spieler benötigt bevor die Runder startet");
            }
        }.runTaskTimerAsynchronously(TTT.getInstance(), 0, 20*30);
    }


}
