package com.nordryd.instance;

import java.util.ArrayList;
import java.util.List;

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
    public static final List<World> LOBBIES = new ArrayList<>();
    public static final List<World> INSTANCES = new ArrayList<>();

    /**
     * TODO: Replace this with when the instance starts, and from there an Instance
     * param will be given, which will have a getType() method<br>
     * <br>
     * 
     * TODO: this will eventually turn into {@code registerInstance},
     * {@code startInstance}, and {@code endInstance}
     */
    public static void createInstance(Instance instance) {
        String name = IValues.WORLD_PREFIX + instance.getName() + "_" + Instance.getNextInstanceId();
        Bukkit.createWorld(new WorldCreator(name).generator(instance.getChunkGenerator()).generateStructures(false));

        InstanceConfig respectiveConfig = instance.getType().equals(InstanceType.ARENA) ? InstanceConfig.ARENAS : InstanceConfig.DUNGEONS;

        List<String> configInstanceList = InstanceConfig.getConfig().getStringList(respectiveConfig.getKey());
        configInstanceList.add(name);
        InstanceConfig.getConfig().set(respectiveConfig.getKey(), configInstanceList);
        InstanceConfig.save();
    }

    /**
     * TODO: in the start_instance command, have each instance be listed, and each
     * instance has a LobbyBiome associated with it for the landscape of the lobby
     * 
     * figure out a way to freeze time in each individual world too
     */
    public static void startInstance(Player player, Instance instance) {
        player.sendMessage(ChatColor.DARK_AQUA + "Generating lobby (this might take a while, and players will be frozen during this time)...");
        String lobbyName = "lobby_" + Instance.getNextInstanceId();
        createLobby(instance, lobbyName);

        player.sendMessage(ChatColor.DARK_AQUA + "Lobby generated. Teleporting party..." + ChatColor.RESET);
        player.teleport(Bukkit.getWorld(lobbyName).getSpawnLocation());
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

    private static void createLobby(Instance instance, String name) {
        World world = Bukkit.createWorld(new WorldCreator(name).generateStructures(false).seed(instance.getSeed()).type(WorldType.FLAT));

        LobbyGenerator.generateLobby(world);
        world.setAutoSave(false);
        world.setTime(instance.getTime());
        world.setSpawnFlags(false, false);
        world.setStorm(true);
    }
}
