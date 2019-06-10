package com.nordryd.enums;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.annotation.GameRegistration;

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
public enum Config
{
    /**
     * <p>
     * If low health effects are active.
     * </p>
     * 
     * <p>
     * Default: <b><i>TRUE</i></b>
     * </p>
     */
    DO_LOW_HEALTH_EFFECTS("doLowHealthEffects", true);

    private final String key;
    private final Object defaultValue;

    private Config(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    /**
     * @return The config key.
     */
    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return this.key;
    }

    private Object getDefault() {
        return defaultValue;
    }

    /**
     * Initializes all the configutation values with their respective defaults.
     * 
     * @param plugin
     *        {@link JavaPlugin} to add config values to.
     */
    @GameRegistration
    public static void registerDefaultConfig(JavaPlugin plugin) {
        for (Config configDefault : Config.values()) {
            plugin.getConfig().addDefault(configDefault.getKey(), configDefault.getDefault());
        }

        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }
}
