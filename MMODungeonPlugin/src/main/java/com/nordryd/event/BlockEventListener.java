package com.nordryd.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.item.AbstractTool;
import com.nordryd.util.IUtility;

/**
 * <p>
 * Class to handle all {@link EntityEvent}s. {@link EntityEvent}s that pertain
 * specifically to players will be handled in {@link PlayerEventListener}.
 * </p>
 * 
 * @author Nordryd
 */
public class BlockEventListener extends EventListener
{
    /**
     * Constructor.
     * 
     * @param plugin
     *        {@link JavaPlugin}
     */
    public BlockEventListener(JavaPlugin jPlugin) {
        super(jPlugin);
    }

    /**
     * TODO: Some dungeons can have blocks that are breakable? (Place metadata on
     * them)
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent bbevent) {
        if (IUtility.isInstanceWorld(bbevent.getPlayer().getWorld())) {
            handleInstanceBlockEvent(bbevent, bbevent.getPlayer());
        }
    }

    private void handleInstanceBlockEvent(BlockBreakEvent bbevent, Player player) {
        if (!player.getInventory().getItemInMainHand().hasItemMeta() || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
                .equals(AbstractTool.ABSTRACT_TOOL_DISPLAY_NAMES.get("Instance Edit Tool"))) {
            bbevent.setCancelled(true);
        }
    }
}
