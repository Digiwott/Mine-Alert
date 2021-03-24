package com.minealert.config;

import com.minealert.main.MineAlert;

import java.util.List;

public class ConfigManager {

    private static ConfigManager instance = new ConfigManager();

    public static ConfigManager getInstance() {
        return instance;
    }

    public int getInteger(String path) {
        return MineAlert.plugin.getConfig().getInt(path);
    }

    public String getString(String path) {
        return MineAlert.plugin.getConfig().getString(path);
    }

    public List<String> getStringList(String path) {
        return MineAlert.plugin.getConfig().getStringList(path);
    }
}

