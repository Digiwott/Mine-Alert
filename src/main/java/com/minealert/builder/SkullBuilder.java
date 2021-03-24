package com.minealert.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

public class SkullBuilder {

    public static class Builder {

        private String name;
        private String owner;

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder skullName(String name) {
            this.name = name;
            return this;
        }

        public Builder skullOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public ItemStack build() {
            ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
            SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
            skullMeta.setDisplayName(name);
            skullMeta.setOwner(owner);
            skullItem.setItemMeta(skullMeta);
            return skullItem;
        }
    }

    private SkullBuilder() {

    }
}
