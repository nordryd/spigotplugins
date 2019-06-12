package com.nordryd.item;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.nordryd.world.RegionBuilder;

/**
 * <p>
 * Class to handle all tools. This has to be done this way, rather than with an
 * interface and {@link ItemStack} derived class, because of how Minecraft
 * stores items. Doing it the aforementioned way would require the use of
 * {@code NBTTag}s.
 * </p>
 * 
 * @author Nordryd
 */
public class ToolHandler
{
    private static Optional<RegionBuilder> regionBuilder = Optional.empty();

    /**
     * Handler for the RegionTool
     * 
     * @param location
     *        The {@link Location} of the block.
     * @param block
     *        The {@link Block}
     */
    public static void onRegionToolUse(Location location, Block block) {
        if (!regionBuilder.isPresent()) {
            regionBuilder = Optional.ofNullable(new RegionBuilder(location, block));
        }
        else {
            regionBuilder.get().addPoint(location, block);
        }
    }
}
