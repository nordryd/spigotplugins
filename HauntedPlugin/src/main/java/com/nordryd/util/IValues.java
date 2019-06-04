package com.nordryd.util;

import org.bukkit.ChatColor;

/**
 * <p>
 * Interface for various values used throughout the plugin. This is to reduce
 * the prevalence of magic values throughout the code.
 * </p>
 * 
 * @author Nordryd
 */
public interface IValues
{
	public static final String PREFIX = ChatColor.DARK_AQUA + "[" + IReference.PLUGIN_NAME + "]" + ChatColor.AQUA;
	public static final String PLAYER_NAME_ESCAPE = "%p";

	public static final double BLOCK_CENTER_OFFSET = 0.5;

	public static final int RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS = 5;

	public static final float[] PITCH = { 0.5f, 1.0f, 2.0f };

	/**
	 * Interface for storing time values (ms).
	 */
	public static interface Time
	{
		public static final long DAY = 0, NOON = 6000, NIGHT = 13000, MIDNIGHT = 18000;
	}
}
