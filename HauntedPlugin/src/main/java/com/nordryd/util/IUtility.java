package com.nordryd.util;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;

import net.minecraft.server.v1_14_R1.WorldServer;

/**
 * <p>
 * Interface for miscellaneous utility functions.
 * </p>
 * 
 * @author Nordryd
 */
public interface IUtility
{
	/**
	 * Convert milliseconds into <i>Minecraft</i> game ticks (20 ticks per second).
	 * 
	 * @param ms
	 *        Time in <b>ms</b>
	 * @return Time in <i>Minecraft</i> ticks.
	 */
	public static long getTicksFromMillis(long ms) {
		return (ms * 1000) / 20;
	}

	/**
	 * Centers the location in the actual geometric center of the given block's
	 * location, rather than its logical origin.
	 * 
	 * @param location
	 *        {@link Location} of the block.
	 * @return The {@link Location} geometric center of the given block.
	 */
	public static Location getCenteredBlockLocation(Location location) {
		return location.add(IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET);
	}

	/**
	 * Convert the {@link org.bukkit.World} into a
	 * {@link net.minecraft.server.v1_14_R1.WorldServer}.
	 * 
	 * @param world
	 *        A {@link org.bukkit.World}
	 * @return A {@link net.minecraft.server.v1_14_R1.WorldServer}
	 */
	public static WorldServer getWorldServerFromWorld(org.bukkit.World world) {
		return ((CraftWorld) world).getHandle();
	}
}
