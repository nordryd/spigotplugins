package com.nordryd.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;

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

	public static final int BEACON_SIN_RADIUS = 6;
	public static final int NULL_EXPLOSION = 0;
	public static final int LIGHTNING_STRIKE_RADIUS = 3;
	
	public static final double BLOCK_CENTER_OFFSET = 0.5;

	public static final float[] PITCH = { 0.5f, 1.0f, 2.0f };

	public static final List<Material> ORES = new ArrayList<>(Arrays.asList(Material.COAL_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE,
			Material.GOLD_ORE, Material.IRON_ORE, Material.NETHER_QUARTZ_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE));

	/**
	 * Interface for storing time values (ms).
	 */
	public static interface Time
	{
		public static final long DAY = 0, NOON = 6000, NIGHT = 13000, MIDNIGHT = 18000;
	}
}
