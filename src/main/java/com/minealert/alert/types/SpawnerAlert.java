package com.minealert.alert.types;

import com.minealert.alert.MineAlertDataTypes;
import com.minealert.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpawnerAlert implements AlertInterface {

    private static SpawnerAlert instance;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final LocalDateTime now = LocalDateTime.now();
    private String type;


    private SpawnerAlert(){

    }

    public static synchronized SpawnerAlert getInstance(){
        if(instance == null){
            instance = new SpawnerAlert();
        }
        return instance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addToDataType(Player miner) {

        //Adding on to the hashmap
        if (MineAlertDataTypes.spawnersMined.containsKey(miner.getName())) {
            MineAlertDataTypes.spawnersMined.put(miner.getName(), MineAlertDataTypes.spawnersMined.get(miner.getName()) + 1);
        }
    }

    public void notifyPlayers(Player miner) {
        //Notify When Mining
        if (MineAlertDataTypes.spawnersMined.get(miner.getName()) >= ConfigManager.getInstance().getInteger("Spawner")) {
            Bukkit.getOnlinePlayers().forEach(spies -> {
                if (!spies.hasPermission("minealert.notify")) {
                    return;
                }

                if (!MineAlertDataTypes.alert.get(spies.getName()) || !MineAlertDataTypes.alert.containsKey(spies.getName())) {
                    return;
                }

                if (MineAlertDataTypes.alert.get(spies.getName())) {
                    String prefix = ConfigManager.getInstance().getString("MineAlertPrefix");
                    String message = ConfigManager.getInstance().getString("MineAlertMinedSpawners");
                    message = message.replace("%time%", dtf.format(now));
                    message = message.replace("%player%", miner.getName());
                    message = message.replace("%amount%", MineAlertDataTypes.spawnersMined.get(miner.getName()).toString());
                    message = message.replace("%mobtype%", type);
                    spies.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
                }
            });
        }
    }

    public void call(Player miner) {
        addToDataType(miner);
        notifyPlayers(miner);
    }
}
