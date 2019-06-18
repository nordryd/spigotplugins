package com.nordryd.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import com.nordryd.config.InstanceConfig;
import com.nordryd.enums.Instance;
import com.nordryd.enums.InstanceEnumHandler.InstanceType;
import com.nordryd.util.IUtility;
import com.nordryd.util.IValues;

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
    public static void createInstance(Instance instance) {
        String name = IValues.WORLD_PREFIX + instance.getName();
        Bukkit.createWorld(new WorldCreator(name).generator(instance.getChunkGenerator()).generateStructures(false));

        if (!instance.getType().equals(InstanceType.LOBBY)) {
            InstanceConfig respectiveConfig = instance.getType().equals(InstanceType.ARENA) ? InstanceConfig.ARENAS : InstanceConfig.DUNGEONS;

            List<String> configInstanceList = InstanceConfig.getConfig().getStringList(respectiveConfig.getKey());
            configInstanceList.add(name);
            InstanceConfig.getConfig().set(respectiveConfig.getKey(), configInstanceList);
            InstanceConfig.save();
        }
    }

    public static void restartInstances() {
        List<String> dungeonNames = InstanceConfig.getConfig().getStringList(InstanceConfig.DUNGEONS.getKey()),
                arenaNames = InstanceConfig.getConfig().getStringList(InstanceConfig.ARENAS.getKey());

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

    public static List<String> getInstanceNames() {
        List<String> worldNames = new ArrayList<>();
        worldNames.add(IValues.HOMEWORLD_STRING);

        for (World world : Bukkit.getWorlds().subList(1, Bukkit.getWorlds().size())) {
            if (!IUtility.isWorldNetherOrEnd(world.getName())) {
                worldNames.add(world.getName().substring(IValues.WORLD_PREFIX.length(), world.getName().length()));
            }
        }

        return worldNames;
    }

    public static Optional<World> getInstanceFromName(String instanceName) {
        Bukkit.broadcastMessage(instanceName);
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
