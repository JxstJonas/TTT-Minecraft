package net.vergessxner.ttt.utils.states;

import net.vergessxner.ttt.utils.countdown.SearchPlayerCountdown;
import net.vergessxner.ttt.utils.countdown.StartLobbyCountdown;
import net.vergessxner.ttt.utils.countdown.TTTCountdown;
import net.vergessxner.ttt.utils.states.provider.IGameState;

/**
 * @author Jonas
 * Created: 02.01.2021
 * Class: LobbyState
 */

public class LobbyState implements IGameState {

    private final TTTCountdown searchCD = new SearchPlayerCountdown(0);
    private final TTTCountdown startCD = new StartLobbyCountdown(60, searchCD);

    @Override
    public void start() {
        searchCD.startCountdown();
        startCD.stopCountdown();
    }

    @Override
    public void stop() {
        searchCD.stopCountdown();
        startCD.stopCountdown();
    }


    public TTTCountdown getSearchCD() {
        return searchCD;
    }

    public TTTCountdown getStartCD() {
        return startCD;
    }
}
