package com.nordryd.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import com.nordryd.enums.Commands;
import com.nordryd.util.IValues;

import net.md_5.bungee.api.ChatColor;

public class InstanceManager
{
    /**
     * <p>
     * All active instances, whether it's an arena or a dungeon.
     * </p>
     * <p>
     * Entry = &lt;Instance ID, World with it's associated instanceOfInstanceID&gt;
     * </p>
     */
    public static final Map<Long, List<World>> ACTIVE_INSTANCES = new HashMap<>();

    public static void createInstance(String instanceName) {
        Bukkit.createWorld(new WorldCreator(IValues.WORLD_PREFIX + instanceName).generator(new CustomChunkGenerator()).generateStructures(false));
    }

    public static List<World> getInstances() {
        return Bukkit.getWorlds();
    }

    public static String getActiveInstanceNames() {
        StringBuilder builder = new StringBuilder(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Active Instances:\n" + ChatColor.RESET + ""
                + ChatColor.BLUE + IValues.HOMEWORLD_STRING + "\n");

        for (World world : Bukkit.getWorlds().subList(1, Bukkit.getWorlds().size())) {
            if (!isWorldNetherOrEnd(world.getName())) {
                builder.append(world.getName().substring(IValues.WORLD_PREFIX.length()) + "\n");
            }
        }

        return builder.append(ChatColor.RESET + "Use " + ChatColor.GREEN + "/" + Commands.PORT_TO_WORLD.getCommand() + ChatColor.RESET
                + " [world name] to port to one of these worlds.").toString();
    }

    public static Optional<World> getInstanceFromName(String instanceName) {
        String name = (instanceName.equals(IValues.HOMEWORLD_STRING)) ? IValues.WORLD_PREFIX.substring(0, IValues.WORLD_PREFIX.length() - 1)
                : IValues.WORLD_PREFIX + instanceName;

        if (!isWorldNetherOrEnd(name)) {
            for (World instance : Bukkit.getWorlds()) {
                Bukkit.broadcastMessage(name + " " + instance.getName());
                if (instance.getName().equals(name)) {
                    Bukkit.broadcastMessage("match found");
                    return Optional.ofNullable(instance);
                }
            }
        }

        return Optional.empty();
    }

    private static boolean isWorldNetherOrEnd(final String worldName) {
        return worldName.equalsIgnoreCase(IValues.WORLD_PREFIX + "nether") || worldName.equalsIgnoreCase(IValues.WORLD_PREFIX + "the_end");
    }
}
