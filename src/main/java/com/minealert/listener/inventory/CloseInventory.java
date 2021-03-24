package com.minealert.listener.inventory;

import com.minealert.builder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CloseInventory implements Listener {

    @EventHandler
    public void onClose(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Inspect Menu")){
            if(ItemBuilder.hasDisplayName(e.getCurrentItem())){
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                if(name.equalsIgnoreCase(ChatColor.RED + "Close Inventory")){
                    player.closeInventory();
                }
            }
        }
    }
}
