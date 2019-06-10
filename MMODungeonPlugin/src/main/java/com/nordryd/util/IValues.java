package com.nordryd.util;

import org.bukkit.ChatColor;

import com.nordryd.annotation.PluginUtility;
import com.nordryd.enums.Commands;

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

    public static final double LOW_HEALTH_THRESHOLD = 6.5;
    public static final double LOW_FOOD_THRESHOLD = 6;
    public static final double LOW_AIR_THRESHOLD = 6.5;
    public static final double BLOCK_CENTER_OFFSET = 0.5;

    public static final int RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS = 5;

    public static final float[] PITCH = { 0.5f, 1.0f, 2.0f };

    /**
     * <p>
     * Interface for any strings involving commands.
     * </p>
     */
    @PluginUtility
    public interface ICmdStrings
    {
        public static final String CMD_PREFIX = "mmod.";

        public static String insufficientPermString(Commands command) {
            return ChatColor.DARK_RED + "Insufficient permission for the command + " + command.getCommand();
        }
    }
}
