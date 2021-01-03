package net.vergessxner.ttt.utils.countdown;

import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

/**
 * @author Jonas
 * Created: 02.01.2021
 * Class: ICountdown
 */

public abstract class TTTCountdown {

    private final int maxTime;
    private int time;
    protected boolean running = false;
    private BukkitTask task;

    public TTTCountdown(int maxTime) {
        this.maxTime = maxTime;
    }

    private final ArrayList<Runnable> startFunctions = new ArrayList<>();
    private final ArrayList<Runnable> stopFunctions = new ArrayList<>();
    private final ArrayList<Runnable> finishFunction = new ArrayList<>();

    public boolean isRunning() {
        return running;
    }

    public int getTime() {
        return time;
    }
    public int setTime(int time) {
        this.time = time;
        return time;
    }

    public void stopCountdown() {
        running = false;
        if(!getStopFunctions().isEmpty())
            getStopFunctions().forEach(Runnable::run);
        if(task != null) {
            task.cancel();
            task = null;
        }
    }

    public ArrayList<Runnable> getStartFunctions() {
        return startFunctions;
    }

    public ArrayList<Runnable> getStopFunctions() {
        return stopFunctions;
    }

    public ArrayList<Runnable> getFinishFunction() {
        return finishFunction;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

    public void addStartFunction(Runnable runnable) {
        startFunctions.add(runnable);
    }

    public void addEndingFunction(Runnable runnable) {
        stopFunctions.add(runnable);
    }

    public void addFinishFunction(Runnable runnable) {
        finishFunction.add(runnable);
    }

    abstract void startCountdown();

}
