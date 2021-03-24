package com.minealert.listener.inventory;

import com.minealert.builder.ItemBuilder;
import com.minealert.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffectType;

public class NightVision implements Listener {

    private String prefix = ConfigManager.getInstance().getString("MineAlertPrefix");

    @EventHandler
    public void giveNightVision(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Settings Menu")) {
            if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                if (name.equalsIgnoreCase(ChatColor.GREEN + "Enable Night Vision")) {
                    e.setCancelled(true);
                    if (!player.hasPermission("minealert.nightvision")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou don't have enough permission to use this option!"));
                        return;
                    }

                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                                ConfigManager.getInstance().getString("NightVisionAlreadyEnabled")));
                        return;
                    }

                    player.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(Integer.MAX_VALUE, 2));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                            ConfigManager.getInstance().getString("NightVisionEnabled")));
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    public void clearEffect(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Settings Menu")) {
            if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                if (name.equalsIgnoreCase(ChatColor.RED + "Clear Night Vision")) {
                    e.setCancelled(true);
                    if (!player.hasPermission("minealert.nightvision")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou don't have enough permission to use this option!"));
                        return;
                    }

                    if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                                ConfigManager.getInstance().getString("NightVisionAlreadyDisabled")));
                        return;
                    }

                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                            ConfigManager.getInstance().getString("NightVisionDisabled")));
                    player.closeInventory();
                }
            }
        }
    }
}
