package com.nordryd.event;

import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.nordryd.enums.ParticleColor;
import com.nordryd.particle.ParticleDust;
import com.nordryd.particle.ParticleHandler;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;

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

		pHandler.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent phevent) {
		Entity entity = phevent.getEntity();

		pHandler.spawnParticles(ParticleSparkle.getBuilder(entity.getLocation(), entity.getWorld()).setCount(10).build());
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent edevent) {
		Entity entity = edevent.getEntity();

		pHandler.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation().add(0.0, 0.75, 0.0), entity.getWorld()).setCount(25)
				.setColor(ParticleColor.RED).build());
	}

	@EventHandler
	public void onLeafDecay(LeavesDecayEvent ldevent) {
		Block leaf = ldevent.getBlock();

		pHandler.spawnParticles(ParticleDust.getBuilder(leaf.getLocation(), leaf.getWorld()).setColor(ParticleColor.GREEN).build());
	}

	@EventHandler
	public void onItemEnchanted(EnchantItemEvent eievent) {
		Player player = eievent.getEnchanter();
		Block eTable = eievent.getEnchantBlock();

		player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation().add(0.0, 1.0, 0.0), 50);
		eTable.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, eTable.getLocation().add(0.0, 1.0, 0.0), 50);
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent esevent) {
		Entity entity = esevent.getEntity();
		pHandler.spawnParticles(
				ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld()).setColor(ParticleColor.CYAN).setCount(15).build());
	}

	@EventHandler
	public void onCreatureSpawnerSpawn(SpawnerSpawnEvent ssevent) {
		Entity entity = ssevent.getEntity();
		CreatureSpawner spawner = ssevent.getSpawner();

		pHandler.spawnParticles(
				ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld()).setColor(ParticleColor.MAGENTA).setCount(15).build(),
				ParticleSparkle.getBuilder(spawner.getLocation(), spawner.getWorld()).setCount(15).build());
	}
}
