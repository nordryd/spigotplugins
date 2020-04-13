package com.nordryd.player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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
    RETURN_LOCATIONS("return_locations"),

    /**
     * <p>
     * Stores a player's location on logout to determine if they are instanced or
     * not.
     * </p>
     */
    WORLD_ON_LOGOUT("world_on_logout");

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
        for (PlayerConfig config : PlayerConfig.values()) {
            if (!CONFIG.contains(config.getKey())) {
                CONFIG.set(config.getKey(), new ArrayList<>());
            }
        }

        save();

        JPLUGIN.getLogger().info(ANSIColor.GREEN + "PlayerConfig registered successfully!" + ANSIColor.RESET);
    }

    /**
     * Sets a player's value in the specified string list.
     * 
     * @param player
     *        {@link Player}
     * @param value
     *        The value to associate with the player.
     */
    public void setPlayerValue(Player player, String value) {
        List<String> storedValues = CONFIG.getStringList(this.getKey());
        int registeredPlayerIndex = getRegisteredPlayerIndexInConfig(player);

        if (registeredPlayerIndex != -1) {
            storedValues.remove(registeredPlayerIndex);
        }

        storedValues.add(player.getName() + " " + value);

        CONFIG.set(this.getKey(), storedValues);
        save();
    }

    /**
     * Removes & returns a player value from the config list.
     * 
     * @param player
     *        {@link Player}
     * @return The removed value if the player is registered. Otherwise returns an
     *         empty string.
     */
    public String removePlayerValue(Player player) {
        List<String> storedValues = CONFIG.getStringList(this.getKey());
        int registeredPlayerIndex = getRegisteredPlayerIndexInConfig(player);

        if (registeredPlayerIndex != -1) {
            String removedValue = storedValues.get(registeredPlayerIndex);
            storedValues.remove(registeredPlayerIndex);
            CONFIG.set(this.getKey(), storedValues);
            save();

            return removedValue;
        }

        return "";
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

        file = new File(JPLUGIN.getDataFolder(), "players.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                JPLUGIN.getLogger().info(ANSIColor.GREEN + "The players.yml file has been created" + ANSIColor.RESET);
            }
            catch (IOException ioe) {
                JPLUGIN.getLogger().info(ANSIColor.RED + "Could not create players.yml" + ANSIColor.RESET);
            }
        }

        CONFIG = YamlConfiguration.loadConfiguration(file);
    }

    private int getRegisteredPlayerIndexInConfig(Player player) {
        List<String> registered = PlayerConfig.getConfig().getStringList(this.getKey());

        for (int registeredPlayerIndex = 0; registeredPlayerIndex < registered.size(); registeredPlayerIndex++) {
            if (registered.get(registeredPlayerIndex).contains(player.getName())) {
                return registeredPlayerIndex;
            }
        }

        return -1;
    }
}
