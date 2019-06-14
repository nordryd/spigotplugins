package com.nordryd.event;

import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ColorEnumHandler.ParticleColor;
import com.nordryd.enums.Config;
import com.nordryd.item.AbstractTool;
import com.nordryd.item.AbstractToolHandler;
import com.nordryd.particle.ParticleFactory;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.IMetadata;
import com.nordryd.util.IValues;

/**
 * <p>
 * Class to handle all {@link PlayerEvent}s, or events that specifically involve
 * players (like {@link EntityEvent}s that are only handled for
 * {@code EntityType.PLAYER}).
 * </p>
 * 
 * @author Nordryd
 */
public class PlayerEventListener extends EventListener
{
    /**
     * Constructor.
     * 
     * @param jPlugin
     *        {@code JavaPlugin}
     */
    public PlayerEventListener(JavaPlugin jPlugin) {
        super(jPlugin);
    }

    /**
     * <p>
     * Handler for when a {@link Player} joins the game.
     * </p>
     * <p>
     * TODO: Players spawn at 0,0 as per the custom generators. This is incorrect.
     * This will be handled by their "homeworld_location" parameter. Set their
     * location to that if their instance has been deleted.
     * </p>
     *
     * 
     * @param pjevent
     *        {@link PlayerJoinEvent}
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent pjevent) {
        Player player = pjevent.getPlayer();
        player.setMetadata(IMetadata.PLAYER_HEALTH_LOW, IMetadata.getMetadataValue(jPlugin, false));
        player.setMetadata(IMetadata.PLAYER_INSTANCE_EDIT_MODE, IMetadata.getMetadataValue(jPlugin, false));
        checkPlayerHealth(player);
    }

    /**
     * Handler for when the {@link Player} is hit and takes damage.
     * 
     * @param edevent
     *        {@link EntityDamageEvent}
     */
    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent edevent) {
        if (jPlugin.getConfig().getBoolean(Config.DO_LOW_HEALTH_EFFECTS.getKey()) && edevent.getEntityType().equals(EntityType.PLAYER)) {
            checkPlayerHealth((Player) edevent.getEntity());
        }
    }

    /**
     * Handler for when the {@link Player} regains health.
     * 
     * @param erhevent
     *        {@link EntityRegainHealthEvent}
     */
    @EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent erhevent) {
        if (jPlugin.getConfig().getBoolean(Config.DO_LOW_HEALTH_EFFECTS.getKey()) && erhevent.getEntityType().equals(EntityType.PLAYER)) {
            checkPlayerHealth((Player) erhevent.getEntity());
        }
    }

    private void checkPlayerHealth(Player player) {
        if (!player.getMetadata(IMetadata.PLAYER_HEALTH_LOW).get(0).asBoolean()
                && (player.getHealth() <= jPlugin.getConfig().getDouble(Config.LOW_HEALTH_THRESHOLD.getKey()))) {
            player.setMetadata(IMetadata.PLAYER_HEALTH_LOW, IMetadata.getMetadataValue(jPlugin, true));
            player.sendMessage(ChatColor.RED + "[WARNING] " + ChatColor.DARK_RED + "Health is low!");
            player.playSound(player.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, 1.0f, IValues.PITCH[1]);
            ParticleFactory.spawnParticles(ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1, 0), player.getWorld())
                    .setColors(ParticleColor.RED, ParticleColor.FIREBRICK, ParticleColor.ORANGE).setCount(50).build());
        }
        else if (player.getMetadata(IMetadata.PLAYER_HEALTH_LOW).get(0).asBoolean()
                && (player.getHealth() > jPlugin.getConfig().getDouble(Config.LOW_HEALTH_THRESHOLD.getKey()))) {
            player.setMetadata(IMetadata.PLAYER_HEALTH_LOW, IMetadata.getMetadataValue(jPlugin, false));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent pievent) {
        if (Optional.ofNullable(pievent.getItem()).isPresent()) {
            if (pievent.getItem().getItemMeta().getDisplayName().equals(AbstractTool.ABSTRACT_TOOL_DISPLAY_NAMES.get("Region Edit Tool"))) {
                if (pievent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    AbstractToolHandler.onRegionEditToolUse(pievent.getClickedBlock().getLocation(), pievent.getClickedBlock());
                }
                else {

                }
                pievent.setCancelled(true);
            }
        }
    }
}
