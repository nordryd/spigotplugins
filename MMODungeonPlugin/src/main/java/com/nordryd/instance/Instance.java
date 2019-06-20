package com.nordryd.instance;

import org.bukkit.generator.ChunkGenerator;

import com.nordryd.enums.InstanceEnumHandler.InstanceDifficulty;
import com.nordryd.enums.InstanceEnumHandler.InstanceType;
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
     * Lobby
     */
    LOBBY("lobby", InstanceType.LOBBY, InstanceDifficulty.EASY, null, ITime.NIGHT - 250),

    /**
     * Dungeons
     */
    STOCKADE("the_stockade", InstanceType.DUNGEON, InstanceDifficulty.EASY, new SkyGenerator(), ITime.MIDNIGHT),

    /**
     * Arenas
     */
    SKY("sky_arena", InstanceType.ARENA, InstanceDifficulty.EASY, new SkyGenerator(), ITime.NIGHT - 250);

    private final String name;
    private final InstanceType type;
    private final InstanceDifficulty difficulty;
    private final ChunkGenerator cGenerator;
    private final long time;

    private Instance(String name, InstanceType type, InstanceDifficulty difficulty, ChunkGenerator cGenerator, long time) {
        this.name = name;
        this.type = type;
        this.difficulty = difficulty;
        this.cGenerator = cGenerator;
        this.time = time;
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

    @Override
    public String toString() {
        return ChatColor.AQUA + this.name + ChatColor.RESET + ", type = " + this.type + ", difficulty = " + this.difficulty;
    }
}
