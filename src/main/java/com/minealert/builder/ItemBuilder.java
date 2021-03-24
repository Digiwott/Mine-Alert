package com.minealert.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    public static class Builder {

        private Material material;
        private int amount;
        private String name;
        private List<String> lore;
        private short id;

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder itemType(Material material) {
            this.material = material;
            return this;
        }

        public Builder itemAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder itemName(String name) {
            this.name = name;
            return this;
        }

        public Builder itemLore(String... lores) {
            this.lore = Arrays.asList(lores);
            return this;
        }

        public Builder itemID(short itemId){
            this.id = itemId;
            return this;
        }

        public ItemStack build() {
            ItemStack item = new ItemStack(material, amount);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }

        public ItemStack buildWithID() {
            ItemStack item = new ItemStack(material, amount, id);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }
    }


    private ItemBuilder() {

    }

    public static boolean hasDisplayName(ItemStack currentitem) {
        if (currentitem == null) {
            return false;
        }
        if (!currentitem.hasItemMeta()) {
            return false;
        }
        if (!currentitem.getItemMeta().hasDisplayName()) {
            return false;
        }
        return true;
    }
}