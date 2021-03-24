package com.minealert.inventory;

import com.minealert.builder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsInventory implements InterfaceInventory {

    private static SettingsInventory instance;
    private Inventory inventory;
    private Player player;

    private SettingsInventory() {

    }

    public static synchronized SettingsInventory getInstance() {
        if (instance == null) {
            instance = new SettingsInventory();
        }
        return instance;
    }

    public SettingsInventory define(Player player) {
        this.player = player;
        register();
        return this;
    }

    @Override
    public void create() {
        inventory = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Settings Menu");
    }

    @Override
    public void addItems() {
        //Enable Item PickUp
        inventory.setItem(1, ItemBuilder.Builder.getInstance().itemType(Material.EMERALD).itemAmount(1).itemName(ChatColor.GREEN + "Enable Item Pickup").itemLore(
                ChatColor.GRAY + "Click this to enable Item Pick Up").build());
        //Enable Night Vision
        inventory.setItem(2, ItemBuilder.Builder.getInstance().itemType(Material.ENDER_EYE).itemAmount(1).itemName(ChatColor.GREEN + "Enable Night Vision").itemLore(
                ChatColor.GRAY + "Click this to enable Night Vision").build());
        //Enable Spectator Mode
        inventory.setItem(3, ItemBuilder.Builder.getInstance().itemType(Material.GHAST_TEAR).itemAmount(1).itemName(ChatColor.GREEN + "Enable Spectator Mode").itemLore(
                ChatColor.GRAY + "Click this to enable Spectator Mode").build());
        //Disable Item PickUp
        inventory.setItem(5, ItemBuilder.Builder.getInstance().itemType(Material.REDSTONE).itemAmount(1).itemName(ChatColor.RED + "Disable Item Pickup").itemLore(
                ChatColor.GRAY + "Click this to disable Item Pick Up").build());
        //Disable Night Vision
        inventory.setItem(6, ItemBuilder.Builder.getInstance().itemType(Material.MILK_BUCKET).itemAmount(1).itemName(ChatColor.RED + "Clear Night Vision").itemLore(
                ChatColor.GRAY + "Click this to clear your Night Vision").build());
        //Enable Gamemode Survival
        inventory.setItem(7, ItemBuilder.Builder.getInstance().itemType(Material.APPLE).itemAmount(1).itemName(ChatColor.RED + "Disable Spectator Mode").itemLore(
                ChatColor.GRAY + "Click this to go into Survival Mode").build());

    }

    @Override
    public void open() {
        player.openInventory(inventory);
    }

    @Override
    public void register() {
        create();
        addItems();
        open();
    }
}
