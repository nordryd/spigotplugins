package com.nordryd.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public abstract class AbstractConfig
{
    private final JavaPlugin jPlugin;
    private final String name;
    private final File file;
    private final FileConfiguration config;
    private final boolean isDefaultConfig;

    /**
     * Constructor for configs other than the default.
     * 
     * @param jPlugin
     *        {@link JavaPlugin}
     */
    protected AbstractConfig(String name, JavaPlugin jPlugin) {
        this.jPlugin = jPlugin;
        this.name = name;
        this.isDefaultConfig = false;
        if (!jPlugin.getDataFolder().exists()) {
            jPlugin.getDataFolder().mkdir();
        }

        this.file = new File(jPlugin.getDataFolder(), name);

        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException ioe) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create " + name);
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + name + " was created successfully!");
    }

    /**
     * Constructor for the default config.
     * 
     * @param jPlugin
     *        {@link JavaPlugin}
     */
    protected AbstractConfig(JavaPlugin jPlugin) {
        this.jPlugin = jPlugin;
        this.name = "config.yml";
        this.file = null;
        this.config = this.jPlugin.getConfig();
        this.isDefaultConfig = true;
    }

    public String getName() {
        return this.name;
    }

    public File getFile() {
        return this.file;
    }

    public void saveConfig() {
        if (isDefaultConfig) {
            jPlugin.saveConfig();
        }
        else {
            try {
                this.config.save(this.file);
            }
            catch (IOException ioe) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save config " + name);
            }
        }
    }

    public FileConfiguration getConfig() {
        return this.config;
    }
    
    public boolean isDefaultConfig() {
        return this.isDefaultConfig;
    }

    public abstract Map<String, Object> getConfigMap();
}