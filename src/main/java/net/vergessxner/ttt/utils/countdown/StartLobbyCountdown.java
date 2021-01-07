package net.vergessxner.ttt.utils.countdown;

import net.vergessxner.ttt.TTT;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Jonas
 * Created: 02.01.2021
 * Class: StartLobbyCountdown
 */

public class StartLobbyCountdown extends TTTCountdown {

    private BukkitTask task;
    private TTTCountdown searchCD;

    public StartLobbyCountdown(int cdTime, TTTCountdown searchCD) {
        super(cdTime);
        this.searchCD = searchCD;
    }

    @Override
    public void startCountdown() {
        if(searchCD.isRunning()) searchCD.stopCountdown();

        setRunning(true);
        if(!getStartFunctions().isEmpty())
            getStartFunctions().forEach(Runnable::run);
        setTime(getMaxTime());
        task = new BukkitRunnable() {
            @Override
            public void run() {
                switch (getTime()) {
                    case 60: case 30: case 20: case 10: case 5: case 4: case 3: case 2: case 1:
                        Bukkit.broadcastMessage(TTT.PREFIX + "§7Die Runde startet in §a" + (getTime() == 1 ? "einer" : getTime()) + "§7 Sekunden!");
                        break;
                    case 0:
                        Bukkit.broadcastMessage(TTT.PREFIX + "§7Die Runde wird gestartet!");
                        if(!getFinishFunction().isEmpty())
                            getFinishFunction().forEach(Runnable::run);
                        stopCountdown();
                        break;
                    default:
                        break;
                }
                setTime(getTime() - 1);
            }
        }.runTaskTimerAsynchronously(TTT.getInstance(), 0, 20);
    }

}
