package com.minealert.listener.block;

import com.minealert.alert.types.*;
import com.minealert.config.ConfigManager;
import com.minealert.patches.PatchesUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

public class BlockBreak implements Listener {

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        Player miner = e.getPlayer();

        //Checks for players that are whitelisted and won't be flagged for mining
        List<String> whiteList = ConfigManager.getInstance().getStringList("Whitelisted");
        if (whiteList.contains(miner.getName())) {
            return;
        }

        Block block = e.getBlock();
        if (PatchesUtil.getInstance().containsLocation(block.getLocation())) return;
        Material type = block.getType();
        switch (type) {
            //Called when someone mines coal ore.
            case COAL_ORE:
            case DEEPSLATE_COAL_ORE:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                CoalAlert.getInstance().call(miner);
                break;
            //Called when someone mines iron ore.
            case IRON_ORE:
            case DEEPSLATE_IRON_ORE:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                IronAlert.getInstance().call(miner);
                break;
            //Called when someone mines gold ore.
            case GOLD_ORE:
            case DEEPSLATE_GOLD_ORE:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                GoldAlert.getInstance().call(miner);
                break;
            //Called when someone mines lapis ore.
            case LAPIS_ORE:
            case DEEPSLATE_LAPIS_ORE:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                LapisAlert.getInstance().call(miner);
                break;
            //Called when someone mines redstone ore.
            case REDSTONE_ORE:
            case DEEPSLATE_REDSTONE_ORE:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                RedstoneAlert.getInstance().call(miner);
                break;
            //Called when someone mines diamond ore.
            case DIAMOND_ORE:
            case DEEPSLATE_DIAMOND_ORE:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                DiamondAlert.getInstance().call(miner);
                break;
            //Called when someone mines emerald ore.
            case EMERALD_ORE:
            case DEEPSLATE_EMERALD_ORE:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                EmeraldAlert.getInstance().call(miner);
                break;
            case ANCIENT_DEBRIS:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                AncientDebrisAlert.getInstance().call(miner);
                break;
            case NETHER_GOLD_ORE:
                PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                NetherGoldAlert.getInstance().call(miner);
                break;
            //Called when someone mines a spawner.
            case SPAWNER:
                EntityType entityType = ((CreatureSpawner) block.getState()).getSpawnedType();
                if (entityType == EntityType.SKELETON || entityType == EntityType.ZOMBIE || entityType == EntityType.BLAZE ||
                        entityType == EntityType.CAVE_SPIDER || entityType == EntityType.SPIDER) {
                    SpawnerAlert.getInstance().setType(entityType.getName());
                    PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                    SpawnerAlert.getInstance().call(miner);
                }
                break;

            default:
                break;
        }

        //Newer Version Checks
        if (Bukkit.getVersion().contains("1.16")) {
            switch (type) {
                case ANCIENT_DEBRIS:
                    PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                    AncientDebrisAlert.getInstance().call(miner);
                    break;
                case NETHER_GOLD_ORE:
                    PatchesUtil.getInstance().removeBlockLocation(block.getLocation());
                    NetherGoldAlert.getInstance().call(miner);
                default:
                    break;
            }
        }
    }
}
