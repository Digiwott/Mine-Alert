package com.minealert.listener.inventory;

import com.minealert.builder.ItemBuilder;
import com.minealert.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.ArrayList;
import java.util.List;

public class ItemPickUpSetting implements Listener {

    private List<String> itemPickUpPlayer = new ArrayList<>();
    private String prefix = ConfigManager.getInstance().getString("MineAlertPrefix");

    @EventHandler
    public void addToList(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Settings Menu")) {
            if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                if (name.equalsIgnoreCase(ChatColor.RED + "Disable Item Pickup")) {
                    e.setCancelled(true);
                    if (!player.hasPermission("minealert.itempickup")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou don't have enough permission to use this option!"));
                        return;
                    }

                    if (itemPickUpPlayer.contains(player.getName())) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                                ConfigManager.getInstance().getString("ItemPickUpIsAlreadyDisabled")));
                        player.closeInventory();
                        return;
                    }

                    itemPickUpPlayer.add(player.getName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                            ConfigManager.getInstance().getString("ItemPickUpIsDisabled")));
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    public void removeFromList(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Settings Menu")) {
            if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                if (name.equalsIgnoreCase(ChatColor.GREEN + "Enable Item Pickup")) {
                    e.setCancelled(true);
                    if (!player.hasPermission("minealert.itempickup")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou don't have enough permission to use this option!"));
                        return;
                    }

                    if (!itemPickUpPlayer.contains(player.getName())) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                                ConfigManager.getInstance().getString("ItemPickUpIsAlreadyEnabled")));
                        player.closeInventory();
                        return;
                    }

                    itemPickUpPlayer.remove(player.getName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                            ConfigManager.getInstance().getString("ItemPickUpIsEnabled")));
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    public void onPick(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        if (!itemPickUpPlayer.contains(player.getName())) {
            return;
        }

        if (itemPickUpPlayer.contains(player.getName())) {
            e.setCancelled(true);
        }
    }
}
