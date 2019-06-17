package com.nordryd.config;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.util.IValues;

/**
 * <p>
 * Enum for all strings and default values for the plugin configuration, as well
 * as handling their initialization.
 * </p>
 * 
 * <p>
 * TODO Redo to work for specific events (like DO_MOB_AGGRO & DO_MOB_GORE),
 * instead of generalized particles.
 * </p>
 * 
 * @author Nordryd
 */
public class DefaultConfig extends AbstractConfig
{
    public static final Map<String, Object> DEFAULT_CONFIG = new HashMap<>();

    public DefaultConfig(JavaPlugin jPlugin) {
        super(jPlugin);
    }

    static {
        addConfig("doLowHealthEffects", true);
        addConfig("lowHealthThreshold", IValues.DEFAULT_LOW_HEALTH_THRESHOLD);
    }

    @Override
    public Map<String, Object> getConfigMap() {
        return DEFAULT_CONFIG;
    }

    private static void addConfig(String key, Object defaultValue) {
        DEFAULT_CONFIG.put(key, defaultValue);
    }
}
