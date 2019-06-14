package com.nordryd.enums;

import com.nordryd.enums.InstanceEnumHandler.InstanceDifficulty;
import com.nordryd.enums.InstanceEnumHandler.InstanceType;

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
public enum Instances
{
    STOCKADE("The Stockade", InstanceType.DUNGEON, InstanceDifficulty.EASY), SKY("Sky Arena", InstanceType.ARENA, InstanceDifficulty.EASY);

    private final String name;
    private final InstanceType type;
    private final InstanceDifficulty difficulty;

    private Instances(String name, InstanceType type, InstanceDifficulty difficulty) {
        this.name = name;
        this.type = type;
        this.difficulty = difficulty;
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

    @Override
    public String toString() {
        return ChatColor.AQUA + this.name + ChatColor.RESET + ", type = " + this.type + ", difficulty = " + this.difficulty;
    }
}
