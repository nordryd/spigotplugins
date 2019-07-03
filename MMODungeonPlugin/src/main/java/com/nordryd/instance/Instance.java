package com.nordryd.instance;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import com.nordryd.enums.InstanceEnumHandler.InstanceDifficulty;
import com.nordryd.enums.InstanceEnumHandler.InstanceType;
import com.nordryd.util.IUtility;
import com.nordryd.util.IValues;
import com.nordryd.util.IValues.ISeeds;
import com.nordryd.util.IValues.ITime;
import com.nordryd.world.generator.SkyGenerator;

import net.md_5.bungee.api.ChatColor;

/**
 * <p>
 * Enum containing all pre-made instances in the plugin.
 * </p>
 * 
 * <p>
 * TODO: Add parameter, the world generator or the dungeon itself
 * </p>
 * 
 * <p>
 * TODO: Maybe make this the actual Instance class?
 * </p>
 * 
 * @author Nordryd
 */
public enum Instance
{
    /**
     * Dungeons
     */
    STOCKADE("the_stockade", InstanceType.DUNGEON, InstanceDifficulty.EASY, new SkyGenerator(), ITime.MIDNIGHT, ISeeds.JUNGLE_SHORE),

    /**
     * Arenas
     */
    SKY("sky_arena", InstanceType.ARENA, InstanceDifficulty.EASY, new SkyGenerator(), ITime.NIGHT - 250, ISeeds.JUNGLE_SHORE);

    private static long id = 1;

    private final String name;
    private final InstanceType type;
    private final InstanceDifficulty difficulty;
    private final ChunkGenerator cGenerator;
    private final long time, seed;

    private Instance(String name, InstanceType type, InstanceDifficulty difficulty, ChunkGenerator cGenerator, long time, long seed) {
        this.name = name;
        this.type = type;
        this.difficulty = difficulty;
        this.cGenerator = cGenerator;
        this.time = time;
        this.seed = seed;
    }

    public String getName() {
        return this.name;
    }

    public InstanceType getType() {
        return this.type;
    }

    public InstanceDifficulty getDifficulty() {
        return this.difficulty;
    }

    public ChunkGenerator getChunkGenerator() {
        return this.cGenerator;
    }

    public long getTime() {
        return this.time;
    }

    public long getSeed() {
        return this.seed;
    }

    @Override
    public String toString() {
        return ChatColor.AQUA + this.name + ChatColor.RESET + ", type = " + this.type + ", difficulty = " + this.difficulty;
    }

    @Nullable
    public static Instance getInstanceFromName(String name) {
        for (Instance instance : Instance.values()) {
            if (name.equals(instance.getName())) {
                return instance;
            }
        }

        return null;
    }

    @Nullable
    public static World getInstanceWorldFromName(String instanceName) {
        String name = (instanceName.equals(IValues.HOMEWORLD_STRING)) ? IValues.WORLD_PREFIX.substring(0, IValues.WORLD_PREFIX.length() - 1)
                : IValues.WORLD_PREFIX + instanceName;

        if (!IUtility.isWorldNetherOrEnd(name)) {
            for (World instance : Bukkit.getWorlds()) {
                if (instance.getName().equals(name)) {
                    return instance;
                }
            }
        }

        return null;
    }

    @Nullable
    public static Instance getInstanceFromWorld(World world) {
        for (Instance instance : Instance.values()) {
            if (world.getName().contains(instance.getName())) {
                return instance;
            }
        }

        return null;
    }

    public static long getNextInstanceId() {
        if ((id >= Long.MAX_VALUE - 1) || (id <= 0)) {
            id = 1;
        }

        return id++;
    }

    public static String getInstanceWorldName(Instance instance) {
        return instance.getName() + "_" + getNextInstanceId();
    }
}
