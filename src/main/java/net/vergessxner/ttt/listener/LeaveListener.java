package net.vergessxner.ttt.listener;

import net.vergessxner.ttt.TTT;
import net.vergessxner.ttt.utils.states.LobbyState;
import net.vergessxner.ttt.utils.states.provider.GameStateProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Jonas
 * Created: 07.01.2021
 * Class: LeaveListener
 */

public class LeaveListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setLevel(0);


        final GameStateProvider gameStateProvider = new GameStateProvider();

        //LobbyState
        if(gameStateProvider.getCurrentGameState() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) gameStateProvider.getCurrentGameState();

            //SearchLobbyCountdown
            if(lobbyState.getStartCD().isRunning() && Bukkit.getOnlinePlayers().size() < TTT.getInstance().getGameSettings().getMinPlayer()) {
                if(lobbyState.getStartCD().isRunning())
                    lobbyState.getStartCD().stopCountdown();

                lobbyState.getSearchCD().startCountdown();
            }

            event.setQuitMessage(TTT.PREFIX + "§c" + player.getName() + "§7 hat das Spiel verlassen!");
        }

        // TODO: 07.01.2021 Save Player
    }

}
