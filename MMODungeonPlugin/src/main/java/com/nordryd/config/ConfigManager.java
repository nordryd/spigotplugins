package com.nordryd.config;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.util.annotation.GameRegistration;

/**
 * <p>
 * Manager class for all config files.
 * </p>
 * 
 * @author Nordryd
 */
public class ConfigManager
{
    /**
     * Registers all config values in with their respective YAML files.
     * 
     * @param jPlugin
     *        {@link JavaPlugin}
     */
    @GameRegistration
    public static void registerConfigs(JavaPlugin jPlugin) {
        DefaultConfig.DO_LOW_HEALTH_EFFECTS.register();
        InstanceConfig.DUNGEONS.register();
    }
}
