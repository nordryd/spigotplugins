package com.nordryd.enums;

import org.bukkit.generator.ChunkGenerator;

import com.nordryd.enums.InstanceEnumHandler.InstanceDifficulty;
import com.nordryd.enums.InstanceEnumHandler.InstanceType;
import com.nordryd.world.generator.LobbyGenerator;
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
    LOBBY("Lobby", InstanceType.LOBBY, InstanceDifficulty.EASY, new LobbyGenerator()),

    /**
     * Dungeons
     */
    STOCKADE("the_stockade", InstanceType.DUNGEON, InstanceDifficulty.EASY, new SkyGenerator()),

    /**
     * Arenas
     */
    SKY("sky_arena", InstanceType.ARENA, InstanceDifficulty.EASY, new SkyGenerator());

    private final String name;
    private final InstanceType type;
    private final InstanceDifficulty difficulty;
    private final ChunkGenerator cGenerator;

    private Instance(String name, InstanceType type, InstanceDifficulty difficulty, ChunkGenerator cGenerator) {
        this.name = name;
        this.type = type;
        this.difficulty = difficulty;
        this.cGenerator = cGenerator;
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

    @Override
    public String toString() {
        return ChatColor.AQUA + this.name + ChatColor.RESET + ", type = " + this.type + ", difficulty = " + this.difficulty;
    }
}
