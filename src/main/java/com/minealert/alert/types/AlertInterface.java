package com.minealert.alert.types;

import org.bukkit.entity.Player;

public interface AlertInterface {

    void addToDataType(Player miner);

    void notifyPlayers(Player miner);

    void call(Player miner);
}
