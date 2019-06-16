package com.nordryd.world;

import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Commands;
import com.nordryd.enums.Config;
import com.nordryd.enums.Instances;
import com.nordryd.util.IUtility;
import com.nordryd.util.IValues;

import net.md_5.bungee.api.ChatColor;

public class InstanceManager
{
    /**
     * TODO: Replace this with when the instance starts, and from there an Instance
     * param will be given, which will have a getType() method<br>
     * <br>
     * 
     * TODO: this will eventually turn into {@code registerInstance},
     * {@code startInstance}, and {@code endInstance}
     */
    public static void createInstance(String instanceName, JavaPlugin jPlugin) {
        String name = IValues.WORLD_PREFIX + instanceName;
        Bukkit.createWorld(new WorldCreator(name).generator(new SkyGenerator()).generateStructures(false));

        List<String> configInstanceList = jPlugin.getConfig().getStringList(Config.ARENAS.getKey());
        configInstanceList.add(name);
        jPlugin.getConfig().set(Config.ARENAS.getKey(), configInstanceList);
        jPlugin.saveConfig();
    }

    // TODO: Refactor to an enum similar to Config. Maybe combine them into a
    // ConfigEnumHandler
    public static void restartInstances(JavaPlugin jPlugin) {
        List<String> dungeonNames = jPlugin.getConfig().getStringList(Config.DUNGEONS.getKey()),
                arenaNames = jPlugin.getConfig().getStringList(Config.ARENAS.getKey());

        if (!dungeonNames.isEmpty()) {
            for (String dungeonName : dungeonNames) {
                Bukkit.createWorld(new WorldCreator(dungeonName));
            }
        }

        if (!arenaNames.isEmpty()) {
            for (String arenaName : arenaNames) {
                Bukkit.createWorld(new WorldCreator(arenaName));
            }
        }
    }

    public static List<World> getInstances() {
        return Bukkit.getWorlds();
    }

    public static String getAvailableInstances() {
        String string = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Available Instances:\n" + ChatColor.RESET;

        for (Instances instance : Instances.values()) {
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
