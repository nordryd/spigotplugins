package com.nordryd.config;

import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.util.IConfig;
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
public enum DefaultConfig implements IConfig
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
    LOW_HEALTH_THRESHOLD("lowHealthThreshold", IValues.DEFAULT_LOW_HEALTH_THRESHOLD);

    private final String key;
    private final Object defaultValue;

    private DefaultConfig(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
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

    @Override
    public void register() {
        for (DefaultConfig configDefault : DefaultConfig.values()) {
            jPlugin.getConfig().addDefault(configDefault.getKey(), configDefault.getDefault());
        }

        jPlugin.getConfig().options().copyDefaults(true);
        jPlugin.saveConfig();

        jPlugin.getLogger().info(ANSIColor.GREEN + "Config registered successfully!" + ANSIColor.RESET);
    }
}
