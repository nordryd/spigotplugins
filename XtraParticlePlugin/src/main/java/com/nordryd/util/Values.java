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

	public static final double LOW_HEALTH_THRESHOLD = 6.5;

	public static final double BLOCK_CENTER_OFFSET = 0.5;

	public static final float[] PITCH = { 0.5f, 1.0f, 2.0f };

	public static final List<Material> ORES = new ArrayList<>(Arrays.asList(Material.COAL_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE,
			Material.GOLD_ORE, Material.IRON_ORE, Material.NETHER_QUARTZ_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE));
	public static final List<Material> SIGNS = new ArrayList<>(Arrays.asList(Material.ACACIA_SIGN, Material.ACACIA_WALL_SIGN, Material.BIRCH_SIGN,
			Material.BIRCH_WALL_SIGN, Material.DARK_OAK_SIGN, Material.DARK_OAK_WALL_SIGN, Material.JUNGLE_SIGN, Material.JUNGLE_WALL_SIGN,
			Material.OAK_SIGN, Material.OAK_WALL_SIGN, Material.SPRUCE_SIGN, Material.SPRUCE_WALL_SIGN));

	/**
	 * Interface for storing time values (ms).
	 */
	public static interface Time
	{
		public static final long DAY = 0, NOON = 6000, NIGHT = 13000, MIDNIGHT = 18000;
	}
}
