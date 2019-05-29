package com.nordryd.event;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.nordryd.enums.ParticleColor;
import com.nordryd.particle.ParticleHandler;
import com.nordryd.particle.ParticleSparkle;

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

	/**
	 * Constructor.
	 * 
	 * @param pluginConfig
	 *            Plugin configuration.
	 */
	public EventListener(FileConfiguration pluginConfig) {
		this.pHandler = new ParticleHandler(pluginConfig);
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
		// lightning here
	}

	/**
	 * Sparkle when a projectile lands.
	 * 
	 * @param phevent
	 *            {@code ProjectileHitEvent}
	 */
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent phevent) {
		Entity entity = phevent.getEntity();
		pHandler.sparkle(ParticleSparkle.Builder.builder(entity.getLocation(), entity.getWorld()).setCount(25).build());
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

		pHandler.sparkle(ParticleSparkle.Builder.builder(entity.getLocation(), entity.getWorld()).setCount(25).build());
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
		// dust here
	}
	
	@EventHandler
	public void onItemEnchanted(EnchantItemEvent eievent) {
		// book characters here
	}
}
