package com.nordryd.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.nordryd.util.annotation.PluginUtility;

import net.md_5.bungee.api.ChatColor;

/**
 * <p>
 * Utility class for miscellaneous utility functions.
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
    public static long getTicksFromMillis(final long ms) {
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
    public static Location getCenteredBlockLocation(final Location location) {
        return location.add(IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET);
    }

    public static boolean isWorldNetherOrEnd(final String worldName) {
        return worldName.equalsIgnoreCase(IValues.WORLD_PREFIX + "nether") || worldName.equalsIgnoreCase(IValues.WORLD_PREFIX + "the_end");
    }

    public static String getFormattedToolName(String toolName) {
        return ChatColor.LIGHT_PURPLE + "MMOD:" + ChatColor.AQUA + " " + toolName;
    }

    public static boolean isInstanceWorld(World world) {
        return !(world.getName().equals(IValues.WORLD_PREFIX.substring(0, IValues.WORLD_PREFIX.length() - 1))
                || world.getName().equals(IValues.WORLD_PREFIX + "nether") || world.getName().equals(IValues.WORLD_PREFIX + "the_end"));
    }

    public static boolean isPlayerHoldingItem(Player player, ItemStack item) {
        if (!item.hasItemMeta()) {
            return false;
        }

        return isPlayerHoldingItem(player, item.getItemMeta().getDisplayName());
    }

    public static boolean isPlayerHoldingItem(Player player, String displayName) {
        PlayerInventory inventory = player.getInventory();

        return (inventory.getItemInMainHand().hasItemMeta() && inventory.getItemInMainHand().getItemMeta().getDisplayName().equals(displayName))
                || (inventory.getItemInOffHand().hasItemMeta() && inventory.getItemInOffHand().getItemMeta().getDisplayName().equals(displayName));
    }

    public static String getMagicString(String string) {
        return ChatColor.DARK_RED + "" + ChatColor.MAGIC + "M" + ChatColor.RESET + " " + string + ChatColor.RESET + " " + ChatColor.DARK_RED + ""
                + ChatColor.MAGIC + "M" + ChatColor.RESET;
    }
}
