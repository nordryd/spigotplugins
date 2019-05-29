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
import com.nordryd.particle.ParticleDust;
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

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();
		pHandler.sparkle(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(100).build());
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent phevent) {
		Entity entity = phevent.getEntity();
		pHandler.sparkle(ParticleSparkle.getBuilder(entity.getLocation(), entity.getWorld()).setCount(25).build());
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent edevent) {
		Entity entity = edevent.getEntity();
		pHandler.sparkle(ParticleSparkle.getBuilder(entity.getLocation(), entity.getWorld()).setCount(25).build());
	}

	@EventHandler
	public void onLeafDecay(LeavesDecayEvent ldevent) {
		Block leaf = ldevent.getBlock();
		pHandler.dust(ParticleDust.getBuilder(leaf.getLocation(), leaf.getWorld()).setColor(ParticleColor.GREEN).build());
	}

	@EventHandler
	public void onItemEnchanted(EnchantItemEvent eievent) {
		// book characters here
	}
}
