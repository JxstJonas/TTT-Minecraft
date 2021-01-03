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

public class SearchPlayerCountdown extends TTTCountdown{

    private BukkitTask task;

    public SearchPlayerCountdown(int maxTime) {
        super(maxTime);
    }

    @Override
    public void startCountdown() {
        running = true;
        if(!getStartFunctions().isEmpty())
            getStartFunctions().forEach(Runnable::run);
        task = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(TTT.PREFIX + "");
            }
        }.runTaskTimerAsynchronously(TTT.getInstance(), 0, 20*30);
    }


}
