package com.minealert.patches;

import com.google.common.collect.Lists;
import org.bukkit.Location;

import java.util.Collections;
import java.util.List;

public class PatchesUtil {

    private static PatchesUtil instance;
    private final List<Location> blockLocations = Collections.synchronizedList(Lists.newArrayList());

    public static PatchesUtil getInstance() {
        if (instance == null) {
            instance = new PatchesUtil();
        }

        return instance;
    }

    public void addBlockLocation(Location location) {
        if (blockLocations.contains(location)) return;
        blockLocations.add(location);
    }

    public boolean containsLocation(Location location) {
        return blockLocations.contains(location);
    }

    public void removeBlockLocation(Location location) {
        if (!blockLocations.contains(location)) return;
        blockLocations.remove(location);
    }
}