package net.vergessxner.ttt.utils.states.provider;

import net.vergessxner.ttt.TTT;
import net.vergessxner.ttt.utils.states.EndingState;
import net.vergessxner.ttt.utils.states.InGameState;
import net.vergessxner.ttt.utils.states.LobbyState;

/**
 * @author Jonas
 * Created: 02.01.2021
 * Class: GameStateProvider
 */

public class GameStateProvider {

    public static final int LOBBY = 0, IN_GAME = 1, ENDING = 2;
    private final IGameState[] gameStates;

    public GameStateProvider() {
        gameStates = new IGameState[]{new LobbyState(), new InGameState(), new EndingState()};
    }


    private IGameState currentGameState;

    /**
     * Start GameState
     * @param state 0 = LOBBY, 1 = IN_GAME, 2 = ENDING;
     * @return Started GameState
     */
    public IGameState startGameState(int state) {
        if(currentGameState != null) currentGameState.stop();

        currentGameState = gameStates[state];
        currentGameState.start();
        TTT.getInstance().getLogger().info("Gamestate " + gameStates[state].getClass().getSimpleName() + " started!");
        return gameStates[state];
    }


    /**
     * Stop the Current GameState
     * @return Stopped GameState
     */
    public IGameState stopGameState() {
        if(currentGameState != null) currentGameState.stop();
        return currentGameState;
    }

    public IGameState getCurrentGameState() {
        return currentGameState;
    }
}
