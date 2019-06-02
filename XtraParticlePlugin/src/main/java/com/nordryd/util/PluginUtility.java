package com.nordryd.util;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * <p>
 * Interface for miscellaneous utility functions.
 * </p>
 * 
 * @author Nordryd
 */
public interface PluginUtility
{
	/**
	 * <b>True</b> if the material given is that of a tool.
	 * 
	 * @param material
	 *            {@code Material}
	 * @return If the given material is a tool or not.
	 */
	public static boolean isTool(Material material) {
		for (String materialString : Values.TOOLS) {
			if (material.toString().contains(materialString)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Centers the location in the actual geometric center of the given block's
	 * location, rather than its logical origin.
	 * 
	 * @param location
	 *            {@code Location} of the block.
	 * @return The {@code Location} geometric center of the given block.
	 */
	public static Location getCenteredBlockLocation(Location location) {
		return location.add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET);
	}
}
