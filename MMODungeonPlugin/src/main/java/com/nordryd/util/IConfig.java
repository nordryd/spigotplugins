package com.nordryd.util;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.Main;

/**
 * <p>
 * Interface for any config enum.
 * </p>
 * 
 * @author Nordryd
 */
public interface IConfig
{
    public static JavaPlugin JPLUGIN = Main.getPlugin(Main.class);

    /**
     * @return The key for the config entry.
     */
    public String getKey();

    /**
     * <p>
     * Registers all config values. This registers all config entries in the enum,
     * regardless of which particular value it is called from.
     * </p>
     * 
     * <p>
     * Config.<any enum value>.register(JavaPlugin)
     * </p>
     * 
     * @param JPLUGIN
     *        {@link JavaPlugin
     */
    public void register();
}
