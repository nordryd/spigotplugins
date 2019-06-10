package com.nordryd.util;

import org.bukkit.Location;

import com.nordryd.annotation.PluginUtility;

/**
 * <p>
 * Utility interface for miscellaneous utility functions.
 * </p>
 * 
 * @author Nordryd
 */
@PluginUtility
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
}
