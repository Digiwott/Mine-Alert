package com.minealert.inventory;

import com.minealert.alert.MineAlertDataTypes;
import com.minealert.builder.ItemBuilder;
import com.minealert.builder.SkullBuilder;
import com.minealert.main.MineAlert;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InspectInventory implements InterfaceInventory {

    private Inventory inventory;
    private Player player;
    private Player inspector;
    private static InspectInventory instance;

    private InspectInventory(){

    }

    public static synchronized InspectInventory getInstance(){
        if(instance == null){
            instance = new InspectInventory();
        }
        return instance;
    }

    public InspectInventory define(Player targetPlayer, Player inspectorPlayer){
        this.player = targetPlayer;
        this.inspector = inspectorPlayer;
        register();
        return this;
    }

    public void create() {
        inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Inspect Menu");
    }

    public void addItems(){
        //Arrow
        inventory.setItem(0, ItemBuilder.Builder.getInstance().itemType(Material.ARROW).itemAmount(1).itemName(ChatColor.RED + "Close Inventory").build());
        //Skull Item
        inventory.setItem(13, SkullBuilder.Builder.getInstance().skullName(ChatColor.GREEN + player.getName()).skullOwner(player.getName()).build());
        //Coal Item
        inventory.setItem(21, ItemBuilder.Builder.getInstance().itemType(Material.COAL).itemName(ChatColor.DARK_GRAY + "Coal").itemLore(ChatColor.GRAY + "Coal Mined: " + ChatColor.GREEN +
                MineAlertDataTypes.coalMined.get(player.getName())).itemAmount(1).build());
        //Iron Item
        inventory.setItem(22, ItemBuilder.Builder.getInstance().itemType(Material.IRON_INGOT).itemName(ChatColor.GRAY + "Iron").itemLore(ChatColor.GRAY + "Iron Mined: "
                + ChatColor.GREEN + MineAlertDataTypes.ironMined.get(player.getName())).itemAmount(1).build());
        //Gold Item
        inventory.setItem(23, ItemBuilder.Builder.getInstance().itemType(Material.GOLD_INGOT).itemName(ChatColor.GOLD + "Gold").itemLore(ChatColor.GRAY + "Gold Mined: "
                + ChatColor.GREEN + MineAlertDataTypes.goldMined.get(player.getName())).itemAmount(1).build());
        //Lapis Item
        inventory.setItem(30, ItemBuilder.Builder.getInstance().itemType(Material.LAPIS_LAZULI).itemName(ChatColor.DARK_AQUA + "Lapis").itemLore(ChatColor.GRAY + "Lapis Mined: "
                + ChatColor.GREEN + MineAlertDataTypes.lapisMined.get(player.getName())).itemAmount(1).build());
        //Redstone Item
        inventory.setItem(31, ItemBuilder.Builder.getInstance().itemType(Material.REDSTONE).itemName(ChatColor.RED + "Redstone").itemLore(ChatColor.GRAY + "Redstone Mined: "
                + ChatColor.GREEN + MineAlertDataTypes.redstoneMined.get(player.getName())).itemAmount(1).build());
        //Diamond Item
        inventory.setItem(32, ItemBuilder.Builder.getInstance().itemType(Material.DIAMOND).itemName(ChatColor.AQUA + "Diamonds").itemLore(ChatColor.GRAY + "Diamond Mined: "
                + ChatColor.GREEN + MineAlertDataTypes.diamondsMined.get(player.getName())).itemAmount(1).build());
        //Spawners
        inventory.setItem(39, ItemBuilder.Builder.getInstance().itemType(Material.SPAWNER).itemName(ChatColor.GREEN + "Spawners").itemLore(ChatColor.GRAY + "Spawners Mined: "
                + ChatColor.GREEN + MineAlertDataTypes.spawnersMined.get(player.getName())).itemAmount(1).build());
        //Emerald
        inventory.setItem(40, ItemBuilder.Builder.getInstance().itemType(Material.EMERALD).itemName(ChatColor.GREEN + "Emeralds").itemLore(ChatColor.GRAY + "Emeralds Mined: "
                + ChatColor.GREEN + MineAlertDataTypes.emeraldsMined.get(player.getName())).itemAmount(1).build());
        //Timer
        inventory.setItem(45, ItemBuilder.Builder.getInstance().itemType(Material.CLOCK).itemAmount(1).itemName(ChatColor.GRAY + "Timer: " + ChatColor.GREEN + MineAlert.getInterval()
        + ChatColor.GRAY + " Seconds").itemLore(ChatColor.GRAY + "Player's XRay Data Will Set To 0").build());

    }


    public void open() {
        inspector.openInventory(inventory);
    }

    public void register() {
        create();
        addItems();
        open();
    }

}
