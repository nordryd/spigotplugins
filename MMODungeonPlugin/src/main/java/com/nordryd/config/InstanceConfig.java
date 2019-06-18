package com.nordryd.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.util.IConfig;

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

    private static final File file;
    private static final FileConfiguration instanceConfig;

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
        instanceConfig.options().copyDefaults();
        for (InstanceConfig config : InstanceConfig.values()) {
            instanceConfig.set(config.getKey(), new ArrayList<>());
        }

        save();

        jPlugin.getLogger().info(ANSIColor.GREEN + "InstanceConfig registered successfully!" + ANSIColor.RESET);
    }

    /**
     * Saves instance.yml, along with any updates.
     */
    public static void save() {
        try {
            instanceConfig.save(file);
            jPlugin.getLogger().info(ANSIColor.GREEN + "The instance.yml file has been saved" + ANSIColor.RESET);
        }
        catch (IOException ioe) {
            jPlugin.getLogger().info(ANSIColor.RED + "Could not save instance.yml" + ANSIColor.RESET);
        }
    }

    /**
     * @return Get the config file.
     */
    public static FileConfiguration getConfig() {
        return instanceConfig;
    }

    static {
        if (!jPlugin.getDataFolder().exists()) {
            jPlugin.getDataFolder().mkdir();
        }

        file = new File(jPlugin.getDataFolder(), "instance.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                jPlugin.getLogger().info(ANSIColor.GREEN + "The instance.yml file has been created" + ANSIColor.RESET);
            }
            catch (IOException ioe) {
                jPlugin.getLogger().info(ANSIColor.RED + "Could not create instance.yml" + ANSIColor.RESET);
            }
        }

        instanceConfig = YamlConfiguration.loadConfiguration(file);
    }
}
