package com.nordryd.world;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Commands;
import com.nordryd.enums.Instances;
import com.nordryd.util.IUtility;
import com.nordryd.util.IValues;

import net.md_5.bungee.api.ChatColor;

public class InstanceManager
{
    // TODO: Use this to enable the saving and reloading of instance worlds upon server start/stop
    public static void initializeInstance(JavaPlugin jPlugin) {
        FileConfiguration instanceYaml = YamlConfiguration.loadConfiguration(new File(jPlugin.getDataFolder() + "/instances.yml"));
    }

    public static void createInstance(String instanceName) {
        Bukkit.createWorld(new WorldCreator(IValues.WORLD_PREFIX + instanceName).generator(new SkyGenerator()).generateStructures(false));
    }
    
    public static List<World> getInstances() {
        return Bukkit.getWorlds();
    }
    
    public static String getAvailableInstances() {
        String string = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Available Instances:\n" + ChatColor.RESET;
        
        for(Instances instance : Instances.values()) {
            string += instance.toString() + "\n";
        }
        
        return string;
    }

    public static String getActiveInstanceNames() {
        StringBuilder builder = new StringBuilder(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Active Instances:\n" + ChatColor.RESET + ""
                + ChatColor.BLUE + IValues.HOMEWORLD_STRING + "\n");

        for (World world : Bukkit.getWorlds().subList(1, Bukkit.getWorlds().size())) {
            if (!IUtility.isWorldNetherOrEnd(world.getName())) {
                builder.append(world.getName().substring(IValues.WORLD_PREFIX.length()) + "\n");
            }
        }

        return builder.append(ChatColor.RESET + "Use " + ChatColor.GREEN + "/" + Commands.PORT.getCommand() + ChatColor.RESET
                + " [world name] to port to one of these worlds.").toString();
    }

    public static Optional<World> getInstanceFromName(String instanceName) {
        String name = (instanceName.equals(IValues.HOMEWORLD_STRING)) ? IValues.WORLD_PREFIX.substring(0, IValues.WORLD_PREFIX.length() - 1)
                : IValues.WORLD_PREFIX + instanceName;

        if (!IUtility.isWorldNetherOrEnd(name)) {
            for (World instance : Bukkit.getWorlds()) {
                if (instance.getName().equals(name)) {
                    return Optional.ofNullable(instance);
                }
            }
        }

        return Optional.empty();
    }
}
