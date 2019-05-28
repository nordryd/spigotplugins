package com.nordryd.event;

import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.nordryd.sparkle.ParticleHandler;

/**
 * <p>
 * Listener class for all events in the plugin.
 * </p>
 * 
 * @author Nordryd
 */
public class EventListener implements Listener
{
	private final ParticleHandler pHandler;
	private final Random rand;

	/**
	 * Constructor.
	 * 
	 * @param pluginConfig
	 *            Plugin configuration.
	 */
	public EventListener(FileConfiguration pluginConfig) {
		this.pHandler = new ParticleHandler(pluginConfig);
		this.rand = new Random();
	}

	/**
	 * Sparkle when a player joins the game.
	 * 
	 * @param pjevent
	 *            {@code PlayerJoinEvent}
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();
		pHandler.sparkle(player.getLocation(), player.getWorld());
	}

	/**
	 * Sparkle when an entity dies.
	 * 
	 * @param edevent
	 *            {@code EntityDeathEvent}
	 */
	@EventHandler
	public void onEntityDeath(EntityDeathEvent edevent) {
		Entity entity = edevent.getEntity();

		pHandler.sparkle(entity.getLocation(), entity.getWorld());
	}

	/**
	 * Sparkle when a leaf decays.
	 * 
	 * @param ldevent
	 *            {@code LeafDecayEvent}
	 */
	@EventHandler
	public void onLeafDecay(LeavesDecayEvent ldevent) {
		Block leaf = ldevent.getBlock();
		pHandler.sparkle(leaf.getLocation(), leaf.getWorld());
	}
}
