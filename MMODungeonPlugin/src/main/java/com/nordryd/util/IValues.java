package com.nordryd.util;

import org.bukkit.ChatColor;

import com.nordryd.util.annotation.PluginUtility;

/**
 * <p>
 * Interface for various values used throughout the plugin. This is to reduce
 * the prevalence of magic values throughout the code.
 * </p>
 * 
 * @author Nordryd
 */
@PluginUtility
public interface IValues
{
    public static final String PREFIX = ChatColor.DARK_AQUA + "[" + IReference.PLUGIN_NAME + "]" + ChatColor.AQUA;
    public static final String TOOL_NAME_FORMAT = ChatColor.LIGHT_PURPLE + "MMOD:" + ChatColor.AQUA;

    /**
     * Yes, it has the underscore with it.
     */
    public static final String WORLD_PREFIX = "rootworld_";
    public static final String HOMEWORLD_STRING = "homeworld";
    public static final String PARTY_CHAT_PREFIX = ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + "PARTY" + ChatColor.DARK_BLUE + "] " + ChatColor.AQUA;
    public static final String BASE_CMD_USAGE_STRING = ChatColor.DARK_RED + "Usage:" + ChatColor.RED + " /" + IValues.CMD_PREFIX + " [command]";

    public static final int CHUNK_SIZE = 16;
    public static final int RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS = 5;

    public static final double DEFAULT_LOW_HEALTH_THRESHOLD = 6.5;
    public static final double BLOCK_CENTER_OFFSET = 0.5;

    public static final double PLAYER_HEALTH_MAX = 20.0, LOW_HEALTH_THRESHOLD_UNDERFLOW_RESET = 2.5, LOW_HEALTH_THRESHOLD_OVERFLOW_RESET = 17.5;

    public static final String CMD_PREFIX = "mmod";

    public static final float[] PITCH = { 0.5F, 1.0F, 2.0F };

    /**
     * <p>
     * Interface to store time values as defined by <i>Minecraft</i>.
     * </p>
     */
    @PluginUtility
    public interface ITime
    {
        public static final long DAY = 1000, NOON = 6000, NIGHT = 13000, MIDNIGHT = 18000;
    }

    /**
     * <p>
     * Interface for any strings involving clickable signs.
     * </p>
     */
    @PluginUtility
    public interface ISignStrings
    {

    }

    /**
     * <p>
     * Interface for special seeds that generate in desired locations (particularly
     * biomes)
     * </p>
     */
    @PluginUtility
    public interface ISeeds
    {
        public static final long JUNGLE_SHORE = 400055;
    }
}
