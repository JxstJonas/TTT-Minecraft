package net.vergessxner.ttt.listener;

import net.vergessxner.ttt.TTT;
import net.vergessxner.ttt.utils.states.LobbyState;
import net.vergessxner.ttt.utils.states.provider.GameStateProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * @author Jonas
 * Created: 07.01.2021
 * Class: JoinListener
 */

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setLevel(0);


        final GameStateProvider gameStateProvider = new GameStateProvider();

        //LobbyState
        if(gameStateProvider.getCurrentGameState() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) gameStateProvider.getCurrentGameState();

            //StartLobbyCountdown
            if(lobbyState.getSearchCD().isRunning() && Bukkit.getOnlinePlayers().size() == TTT.getInstance().getGameSettings().getMinPlayer()) {
                lobbyState.getSearchCD().stopCountdown();
                lobbyState.getStartCD().startCountdown();
            }

            event.setJoinMessage(TTT.PREFIX + "§a" + player.getName() + "§7 hat das Spiel betreten!");
        }
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onLoginEvent(PlayerLoginEvent loginEvent) {
        final GameStateProvider gameStateProvider = new GameStateProvider();

        //LobbyState
        if(gameStateProvider.getCurrentGameState() instanceof LobbyState && TTT.getInstance().getGameSettings().getMaxPlayer() == Bukkit.getOnlinePlayers().size()) {
            loginEvent.disallow(PlayerLoginEvent.Result.KICK_FULL, TTT.PREFIX + "§cDer Spiel ist bereits voll!");
        }
    }

}
