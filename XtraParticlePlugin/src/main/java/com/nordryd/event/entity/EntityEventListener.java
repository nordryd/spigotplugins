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
import org.bukkit.event.entity.PigZombieAngerEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.nordryd.enums.Config;
import com.nordryd.enums.Metadata;
import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleFactory;
import com.nordryd.particle.ParticleFlame;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.thread.PigZombieAngerEffect;
import com.nordryd.util.IUtility;

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
		if (jPlugin.getConfig().getBoolean(Config.DO_ENCHANTED_WEAPON_PARTICLES.getKey())) {
			Projectile projectile = pjevent.getEntity();
			if (projectile.hasMetadata(Metadata.FIRED_FROM_ENCHANTED_WEAPON.getKey())) {
				ParticleFactory.spawnParticles(ParticleSparkle.getBuilder(projectile.getLocation(), projectile.getWorld()).setCount(10).build());
			}
		}
	}
	
	@SuppressWarnings({"unused"})
	@EventHandler
	public void onPigZombieAnger(PigZombieAngerEvent pzaevent) {
		BukkitTask task = new PigZombieAngerEffect(pzaevent.getEntity()).runTaskTimer(jPlugin, 0, IUtility.getTicksFromMillis(250));
	}

	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent esevent) {
		Entity entity = esevent.getEntity();
		ParticleFactory.spawnParticles(
				ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld()).setColors(ParticleColor.CYAN).setCount(15).build());
	}

	@EventHandler
	public void onMobSpawnerSpawn(SpawnerSpawnEvent ssevent) {
		Entity entity = ssevent.getEntity();
		CreatureSpawner spawner = ssevent.getSpawner();

		ParticleFactory.spawnParticles(
				ParticleSpellEffect.getBuilder(entity.getLocation().add(0, 1, 0), entity.getWorld()).setColors(ParticleColor.MAGENTA).setCount(15)
						.build(),
				ParticleFlame.getBuilder(entity.getLocation().add(0, 1, 0), entity.getWorld()).setCount(20).build(),
				ParticleSpellEffect.getBuilder(IUtility.getCenteredBlockLocation(spawner.getLocation()), spawner.getWorld())
						.setColors(ParticleColor.BLACK, ParticleColor.RED, ParticleColor.GRAY).setCount(10).build());
	}

	@EventHandler
	public void onEntityBreed(EntityBreedEvent ebevent) {
		Entity entity = ebevent.getEntity();

		ParticleFactory.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld())
				.setColors(ParticleColor.IVORY, ParticleColor.MAGENTA).build());
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent edevent) {
		if (jPlugin.getConfig().getBoolean(Config.DO_ENTITY_DEATH_PARTICLES.getKey())) {
			Entity entity = edevent.getEntity();

			ParticleFactory.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation().add(0.0, 1.0, 0.0), entity.getWorld()).setCount(25)
					.setColors(ParticleColor.RED).build());
		}
	}
}
