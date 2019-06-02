package com.nordryd.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;

/**
 * <p>
 * Interface for various values used throughout the plugin. This is to reduce
 * the prevalence of magic values throughout the code.
 * </p>
 * 
 * @author Nordryd
 */
public interface Values
{
	public static final String PREFIX = ChatColor.DARK_AQUA + "[" + Reference.PLUGIN_NAME + "]" + ChatColor.AQUA;
	public static final String PLAYER_NAME_ESCAPE = "%p";
	public static final List<String> TOOLS = new ArrayList<>(Arrays.asList("_PICKAXE", "_AXE", "_SHOVEL", "_HOE", "_SWORD", "TRIDENT"));
	
	public static final double LOW_HEALTH_THRESHOLD = 6.5;
	public static final double LOW_FOOD_THRESHOLD = 6;
	public static final double LOW_AIR_THRESHOLD = 6.5;
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

	/**
	 * Interface for storing all sign strings that cause signs to do various things.
	 */
	public static interface SignStrings
	{
		String DING = "ding", RESET_LEVEL = "reset level", HIT_MEH = "hit meh";
	}
}
