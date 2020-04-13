package com.nordryd.world.region;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import net.md_5.bungee.api.ChatColor;

/**
 * <p>
 * Use this to moldularly build regions with the PointBlock tool
 * </p>
 * <p>
 * TODO: There's a chunk.setRegion() method available. Check that out for
 * setting regions?
 * </p>
 * 
 * @author Nordryd
 */
public class RegionBuilder
{
    private static final Material MARKER = Material.LIME_STAINED_GLASS, FIRST_MARKER = Material.BLUE_STAINED_GLASS;
    private final Map<Location, Material> originalBlocks;
    private final Location firstBlockLocation;
    private boolean isFirstPointPlaced = false, isComplete = false;

    /**
     * Constructor.
     * 
     * @param location
     *        The {@link Location} of the first block of the region.
     * @param block
     *        The original {@link Block} before being replaced with the marker.
     */
    public RegionBuilder(Location location, Block block) {
        this.originalBlocks = new HashMap<>();
        this.addPoint(location, block);
        this.firstBlockLocation = location;
    }

    /**
     * Add a new block to the region.
     * 
     * @param location
     *        The {@link Location} of the block.
     * @param block
     *        The original {@link Block} before being replaced with the marker.
     */
    public void addPoint(Location location, Block block) {
        if (isFirstPointPlaced) { // first point has been placed
            if (location.equals(firstBlockLocation)) { // complete region
                restoreBlocks();
                isComplete = true;
                Bukkit.broadcastMessage(ChatColor.GREEN + "Region completed!");
            }
            else { // Just place marker
                location.getBlock().setType(MARKER);
                Bukkit.broadcastMessage(location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
                originalBlocks.put(location, block.getType());
            }
        }
        else { // first point has not been placed yet
            location.getBlock().setType(FIRST_MARKER);
            isFirstPointPlaced = true;
            Bukkit.broadcastMessage(location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
            originalBlocks.put(location, block.getType());
        }
    }

    /**
     * @return If this region has been completed.
     */
    public boolean isRegionComplete() {
        return isComplete;
    }

    private void restoreBlocks() {
        for (Entry<Location, Material> originalBlock : originalBlocks.entrySet()) {
            originalBlock.getKey().getBlock().setType(originalBlock.getValue());
        }
    }
}
