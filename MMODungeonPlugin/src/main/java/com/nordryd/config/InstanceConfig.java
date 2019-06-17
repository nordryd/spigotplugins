package com.nordryd.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

public class InstanceConfig extends AbstractConfig
{
    public static final Map<String, Object> DEFAULT_CONFIG = new HashMap<>();

    public InstanceConfig(JavaPlugin jPlugin) {
        super("instance.yml", jPlugin);
    }
    
    static {
        addConfig("dungeons");
        addConfig("arenas");
    }

    @Override
    public Map<String, Object> getConfigMap() {
        return DEFAULT_CONFIG;
    }

    private static void addConfig(String key) {
        DEFAULT_CONFIG.put(key, new ArrayList<String>());
    }
}
