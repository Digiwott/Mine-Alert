package com.minealert.listener.inventory;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CancelInventoryClick implements Listener {

    @EventHandler
    public void onCancel(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Inspect Menu") ||
                e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Settings Menu")){
            e.setCancelled(true);
        }
    }
}
