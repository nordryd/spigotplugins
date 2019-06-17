package com.nordryd.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.util.annotation.GameRegistration;

/**
 * <p>
 * Class for managing the registration and retrieval of plugin configuration
 * files.
 * </p>
 * 
 * @author Nordryd
 */
public class ConfigManager
{
    private static final Map<String, AbstractConfig> CONFIGS = new HashMap<>();

    public static final String DEFAULT = "config.yml", INSTANCE = "instance.yml";

    /**
     * Returns the configuration file requested.
     * 
     * @param config
     *        The name of the config file (does not need {@code .yml} with it, but
     *        having it will not harm anything).
     * @return The requested {@link FileConfiguration} or {@code null} if it does
     *         not exist.
     */
    public static FileConfiguration getConfig(String config) {
        return CONFIGS.containsKey(config) ? CONFIGS.get(config).getConfig() : null;
    }

    private static void addNewConfigs(AbstractConfig... configs) {
        for (AbstractConfig config : configs) {
            CONFIGS.put(config.getName(), config);
        }
    }

    @GameRegistration
    public static void registerConfigs(JavaPlugin jPlugin) {
        addNewConfigs(new DefaultConfig(jPlugin), new InstanceConfig(jPlugin));

        for (AbstractConfig abConfig : CONFIGS.values()) {
            FileConfiguration config = abConfig.getConfig();

            if (abConfig.isDefaultConfig()) {
                jPlugin.getConfig().options().copyDefaults();
            }

            for (Entry<String, Object> cEntry : abConfig.getConfigMap().entrySet()) {
                config.addDefault(cEntry.getKey(), cEntry.getValue());
            }

            abConfig.saveConfig();
            jPlugin.getLogger().info(ANSIColor.GREEN + abConfig.getName() + "registered successfully!" + ANSIColor.RESET);
        }
    }
}
