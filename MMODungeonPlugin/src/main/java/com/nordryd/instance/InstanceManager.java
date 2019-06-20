package com.nordryd.instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import com.nordryd.config.InstanceConfig;
import com.nordryd.enums.InstanceEnumHandler.InstanceType;
import com.nordryd.util.IUtility;
import com.nordryd.util.IValues;
import com.nordryd.world.generator.LobbyGenerator;

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
    public static void createInstance(Instance instance) {
        String name = IValues.WORLD_PREFIX + instance.getName();
        World world;
        if (instance.getChunkGenerator() == null) {
            world = Bukkit.createWorld(new WorldCreator(name).generateStructures(false).seed(400055).type(WorldType.FLAT));
            if (instance.getType().equals(InstanceType.LOBBY)) {
                LobbyGenerator.generateLobby(world);
                world.setAutoSave(false);
                world.setTime(instance.getTime());
                world.setSpawnFlags(false, false);
                world.setStorm(true);
            }
        }
        else {
            world = Bukkit.createWorld(new WorldCreator(name).generator(instance.getChunkGenerator()).generateStructures(false));
        }

        if (!instance.getType().equals(InstanceType.LOBBY)) {
            InstanceConfig respectiveConfig = instance.getType().equals(InstanceType.ARENA) ? InstanceConfig.ARENAS : InstanceConfig.DUNGEONS;

            List<String> configInstanceList = InstanceConfig.getConfig().getStringList(respectiveConfig.getKey());
            configInstanceList.add(name);
            InstanceConfig.getConfig().set(respectiveConfig.getKey(), configInstanceList);
            InstanceConfig.save();
        }
    }

    public static void startInstance(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "Generating lobby (this might take a while, and players will be frozen during this time)...");
        createInstance(Instance.LOBBY);

        player.sendMessage(ChatColor.DARK_AQUA + "Lobby generated. Teleporting party..." + ChatColor.RESET);
        player.teleport(getInstanceFromName(Instance.LOBBY.getName()).get().getSpawnLocation());
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
