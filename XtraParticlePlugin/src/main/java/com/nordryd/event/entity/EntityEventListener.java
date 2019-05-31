package com.nordryd.event.entity;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Metadata;
import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;

/**
 * <p>
 * Class to handle all {@code EntityEvent}s. {@code EntityEvent}s that pertain
 * specifically to players will be handled in {@code PlayerEventListener}.
 * </p>
 * 
 * @author Nordryd
 */
public class EntityEventListener extends EventListener
{
	/**
	 * Constructor.
	 * 
	 * @param plugin
	 *            {@code JavaPlugin}
	 */
	public EntityEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent esbevent) {
		Entity arrow = esbevent.getProjectile(), shooter = esbevent.getEntity();

		if (shooter.getType().equals(EntityType.PLAYER)) {
			if (!((Player) shooter).getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
				arrow.setMetadata(Metadata.FIRED_FROM_ENCHANTED_WEAPON.getKey(), new FixedMetadataValue(jPlugin, 0));
			}
		}
	}
	
	@EventHandler
	public void onProjectileHitEvent(ProjectileHitEvent pjevent) {
		Projectile projectile = pjevent.getEntity();
		if (projectile.hasMetadata(Metadata.FIRED_FROM_ENCHANTED_WEAPON.getKey())) {
			pHandler.spawnParticles(ParticleSparkle.getBuilder(projectile.getLocation(), projectile.getWorld()).setCount(10).build());
		}
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent esevent) {
		Entity entity = esevent.getEntity();
		pHandler.spawnParticles(
				ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld()).setColors(ParticleColor.CYAN).setCount(15).build());
	}

	@EventHandler
	public void onCreatureSpawnerSpawn(SpawnerSpawnEvent ssevent) {
		Entity entity = ssevent.getEntity();
		CreatureSpawner spawner = ssevent.getSpawner();

		pHandler.spawnParticles(
				ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld()).setColors(ParticleColor.MAGENTA).setCount(15).build(),
				ParticleSparkle.getBuilder(spawner.getLocation(), spawner.getWorld()).setCount(15).build());
	}
	
	@EventHandler
	public void onEntityBreed(EntityBreedEvent ebevent) {
		Entity entity = ebevent.getEntity();

		pHandler.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld())
				.setColors(ParticleColor.IVORY, ParticleColor.MAGENTA).build());
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent edevent) {
		Entity entity = edevent.getEntity();

		pHandler.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation().add(0.0, 1.0, 0.0), entity.getWorld()).setCount(25)
				.setColors(ParticleColor.RED).build());
	}
}
