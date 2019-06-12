package com.nordryd.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import com.nordryd.enums.Commands;

import net.md_5.bungee.api.ChatColor;

public class InstanceManager
{
    private static final List<World> INSTANCES = new ArrayList<>();
    
    public static void createInstance(String instanceName) {
        Bukkit.createWorld(new WorldCreator("instance_" + instanceName).generator(new CustomChunkGenerator()));
    }

    public static List<World> getInstances() {
        return Bukkit.getWorlds();
    }

    public static String getListOfWorldNames() {
        StringBuilder builder = new StringBuilder("Active Instances:\n");

        for (World world : Bukkit.getWorlds()) {
            builder.append(world.getName() + "\n");
        }

        return builder.append(
                "Use /" + ChatColor.GREEN + Commands.PORT_TO_WORLD.getCommand() + ChatColor.RESET + " [world name] to port to one of these worlds.")
                .toString();
    }

    public static Optional<World> getInstanceFromName(String instanceName) {
        for (World instance : Bukkit.getWorlds()) {
            Bukkit.broadcastMessage(instanceName + " " + instance.getName());
            if (instance.getName().equals(instanceName)) {
                Bukkit.broadcastMessage("match found");
                return Optional.ofNullable(instance);
            }
        }

        return Optional.empty();
    }
    
    public static void unloadInstances() {
        for(World instance : INSTANCES) {
            Bukkit.unloadWorld(instance, false);
        }
    }
}
