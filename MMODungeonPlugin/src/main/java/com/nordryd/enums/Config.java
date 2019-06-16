package com.nordryd.enums;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.util.IValues;
import com.nordryd.util.annotation.GameRegistration;

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
    DO_LOW_HEALTH_EFFECTS("doLowHealthEffects", true),

    /**
     * <p>
     * The threshold at which health is considered low.
     * </p>
     * 
     * <p>
     * Default: <b><i>6.5</i></b>
     * </p>
     */
    LOW_HEALTH_THRESHOLD("lowHealthThreshold", IValues.DEFAULT_LOW_HEALTH_THRESHOLD),

    /**
     * <p>
     * Active <b>dungeon</b> instance names. This is a string with all names
     * separated by spaces.
     * </p>
     */
    DUNGEONS("dungeons", new ArrayList<String>()),

    /**
     * <p>
     * Active <b>Arena</b> instance names. This is a string with all names separated
     * by spaces.
     * </p>
     */
    ARENAS("arenas", new ArrayList<String>());

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
     * @param jPlugin
     *        {@link JavaPlugin} to add config values to.
     */
    @GameRegistration
    public static void registerDefaultConfig(JavaPlugin jPlugin) {
        for (Config configDefault : Config.values()) {
            jPlugin.getConfig().addDefault(configDefault.getKey(), configDefault.getDefault());
        }

        jPlugin.getConfig().options().copyDefaults(true);
        jPlugin.saveConfig();

        jPlugin.getLogger().info(ANSIColor.GREEN + "Config registered successfully!" + ANSIColor.RESET);
    }
}
