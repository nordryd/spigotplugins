package com.nordryd.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.util.IConfig;

/**
 * <p>
 * Enum for all config values for instances. This file will store active
 * instances in case of a server restart.
 * </p>
 * 
 * @author Nordryd
 */
public enum InstanceConfig implements IConfig
{
    /**
     * <p>
     * Active <b>dungeon</b> instance names. This is a string with all names
     * separated by spaces.
     * </p>
     */
    DUNGEONS("dungeons"),

    /**
     * <p>
     * Active <b>Arena</b> instance names. This is a string with all names separated
     * by spaces.
     * </p>
     */
    ARENAS("arenas");

    private final String key;

    private static final File FILE;
    private static final FileConfiguration CONFIG;

    private InstanceConfig(String name) {
        this.key = name;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return this.key;
    }

    @Override
    public void register() {
        CONFIG.options().copyDefaults();
        for (InstanceConfig config : InstanceConfig.values()) {
            if (!CONFIG.contains(config.getKey())) {
                CONFIG.set(config.getKey(), new ArrayList<>());
            }
        }

        save();

        JPLUGIN.getLogger().info(ANSIColor.GREEN + "InstanceConfig registered successfully!" + ANSIColor.RESET);
    }

    /**
     * Saves instance.yml, along with any updates.
     */
    public static void save() {
        try {
            CONFIG.save(FILE);
            JPLUGIN.getLogger().info(ANSIColor.GREEN + "The instance.yml file has been saved" + ANSIColor.RESET);
        }
        catch (IOException ioe) {
            JPLUGIN.getLogger().info(ANSIColor.RED + "Could not save instance.yml" + ANSIColor.RESET);
        }
    }

    /**
     * @return Get the config file.
     */
    public static FileConfiguration getConfig() {
        return CONFIG;
    }

    static {
        if (!JPLUGIN.getDataFolder().exists()) {
            JPLUGIN.getDataFolder().mkdir();
        }

        FILE = new File(JPLUGIN.getDataFolder(), "instance.yml");

        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
                JPLUGIN.getLogger().info(ANSIColor.GREEN + "The instance.yml file has been created" + ANSIColor.RESET);
            }
            catch (IOException ioe) {
                JPLUGIN.getLogger().info(ANSIColor.RED + "Could not create instance.yml" + ANSIColor.RESET);
            }
        }

        CONFIG = YamlConfiguration.loadConfiguration(FILE);
    }
}
