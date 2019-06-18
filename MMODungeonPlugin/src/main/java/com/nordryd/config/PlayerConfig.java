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
 * Enum for storing player information needed for when they log back in
 * </p>
 * 
 * @author Nordryd
 */
public enum PlayerConfig implements IConfig
{
    /**
     * <p>
     * Stores player data needed for when the player logs back in
     * </p>
     */
    PLAYERS("players");

    private final String key;

    private static final File file;
    private static final FileConfiguration CONFIG;

    private PlayerConfig(String name) {
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
            CONFIG.set(config.getKey(), new ArrayList<>());
        }

        save();

        JPLUGIN.getLogger().info(ANSIColor.GREEN + "PlayerConfig registered successfully!" + ANSIColor.RESET);
    }

    /**
     * Saves instance.yml, along with any updates.
     */
    public static void save() {
        try {
            CONFIG.save(file);
            JPLUGIN.getLogger().info(ANSIColor.GREEN + "The players.yml file has been saved" + ANSIColor.RESET);
        }
        catch (IOException ioe) {
            JPLUGIN.getLogger().info(ANSIColor.RED + "Could not save players.yml" + ANSIColor.RESET);
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

        file = new File(JPLUGIN.getDataFolder(), "instance.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                JPLUGIN.getLogger().info(ANSIColor.GREEN + "The instance.yml file has been created" + ANSIColor.RESET);
            }
            catch (IOException ioe) {
                JPLUGIN.getLogger().info(ANSIColor.RED + "Could not create instance.yml" + ANSIColor.RESET);
            }
        }

        CONFIG = YamlConfiguration.loadConfiguration(file);
    }
}
