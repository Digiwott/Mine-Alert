package com.minealert.commands;

import com.minealert.alert.MineAlertDataTypes;
import com.minealert.config.ConfigManager;
import com.minealert.inventory.InspectInventory;
import com.minealert.inventory.SettingsInventory;
import com.minealert.main.MineAlert;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MineAlertCommands extends BukkitCommand {

    private String prefix = ConfigManager.getInstance().getString("MineAlertPrefix");
    private String disabled = ConfigManager.getInstance().getString("MineAlertDisabled");
    private String enabled = ConfigManager.getInstance().getString("MineAlertEnabled");

    public MineAlertCommands(String name) {
        super(name);
        this.setPermissionMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou don't have enough permission to use this command!"));
        this.setAliases(Arrays.asList("ma", "malert"));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("minealert.use"))) {
            player.sendMessage(getPermissionMessage());
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&f/minealert toggle"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&f/minealert interval"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&f/minealert inspect <player>"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&f/minealert settings"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&f/minealert reload"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&f/minealert resetdata"));
            return true;
        }

        if (args[0].equalsIgnoreCase("toggle")) {
            if (!MineAlertDataTypes.alert.containsKey(player.getName()) || !MineAlertDataTypes.alert.get(player.getName())) {
                MineAlertDataTypes.alert.put(player.getName(), true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + enabled));

                return true;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + disabled));
            MineAlertDataTypes.alert.put(player.getName(), false);
            return true;
        }

        if (args[0].equalsIgnoreCase("interval")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Mine Alert Interval: &a" + MineAlert.getInterval()));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!player.hasPermission("minealert.admin")) {
                player.sendMessage(getPermissionMessage());
                return true;
            }

            MineAlert.plugin.reloadConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have successfully reloaded the &aMine Alert &7config!"));
            return true;
        }

        if (args[0].equalsIgnoreCase("resetdata")) {
            if (!player.hasPermission("minealert.admin")) {
                player.sendMessage(getPermissionMessage());
                return true;
            }

            MineAlert.resetDataTypes();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have successfully resetted players data by force!"));
            return true;
        }

        if (args[0].equalsIgnoreCase("inspect")) {
            if (args.length <= 1) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&f/minealert inspect <player>"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7That player doesn't exist!"));
                return true;
            }

            InspectInventory.getInstance().define(target, player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have opened the inspect menu!"));
            return true;
        }

        if(args[0].equalsIgnoreCase("settings")){
            if(!player.hasPermission("minealert.settings")){
                player.sendMessage(getPermissionMessage());
                return true;
            }

            SettingsInventory.getInstance().define(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have opened the settings menu!"));
        }

        return true;
    }
}
