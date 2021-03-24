package com.minealert.listener.connection;

import com.minealert.alert.MineAlertDataTypes;
import com.minealert.main.MineAlert;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectingListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        MineAlert.addToDataTypes(e.getPlayer());
        if (e.getPlayer().hasPermission("minealert.use")) {
            MineAlertDataTypes.alert.put(e.getPlayer().getName(), true);
        }

        if(e.getPlayer().getGameMode() == GameMode.SPECTATOR){
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        MineAlert.removeFromDataTypes(e.getPlayer());
        MineAlertDataTypes.alert.remove(e.getPlayer().getName());
    }
}
