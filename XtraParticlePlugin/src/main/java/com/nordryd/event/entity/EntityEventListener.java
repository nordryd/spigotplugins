package com.nordryd.event.entity;

import java.util.Optional;

import org.bukkit.Effect;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Config;
import com.nordryd.enums.Metadata;
import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleFlame;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.PluginUtility;
import com.nordryd.util.Values;

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
			pFactory.spawnParticles(ParticleSparkle.getBuilder(projectile.getLocation(), projectile.getWorld()).setCount(10).build());
		}
	}

	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent esevent) {
		Entity entity = esevent.getEntity();
		pFactory.spawnParticles(
				ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld()).setColors(ParticleColor.CYAN).setCount(15).build());
	}

	// TODO Change this because target = friendly mob moving towards favorite item.
	// change to be if it's instanceof Creature?
	// Give farm animals hearts when they target you with their favorite item
	@EventHandler
	public void onHostileMobAggro(EntityTargetLivingEntityEvent etleevent) {
		if (jPlugin.getConfig().getBoolean(Config.DO_MOB_AGGRO.getKey())) {
			if (Optional.ofNullable(etleevent.getTarget()).isPresent() && (etleevent.getEntity() instanceof LivingEntity)) {
				LivingEntity attacker = (LivingEntity) etleevent.getEntity(), target = etleevent.getTarget();

				if (PluginUtility.canAttack(attacker) && !(Values.INVALID_HOSTILE_REASONS.contains(etleevent.getReason()))) {

					attacker.getWorld().playEffect(attacker.getEyeLocation(), Effect.SMOKE, BlockFace.UP);

					if (target.getType().equals(EntityType.PLAYER)) {
						attacker.getWorld().playEffect(attacker.getEyeLocation(), Effect.MOBSPAWNER_FLAMES, 0);
					}
				}
			}
		}
	}

	@EventHandler
	public void onMobSpawnerSpawn(SpawnerSpawnEvent ssevent) {
		Entity entity = ssevent.getEntity();
		CreatureSpawner spawner = ssevent.getSpawner();

		pFactory.spawnParticles(
				ParticleSpellEffect.getBuilder(entity.getLocation().add(0, 1, 0), entity.getWorld()).setColors(ParticleColor.MAGENTA).setCount(15)
						.build(),
				ParticleFlame.getBuilder(entity.getLocation().add(0, 1, 0), entity.getWorld()).setCount(20).build(),
				ParticleSpellEffect.getBuilder(PluginUtility.getCenteredBlockLocation(spawner.getLocation()), spawner.getWorld())
						.setColors(ParticleColor.BLACK, ParticleColor.RED, ParticleColor.GRAY).setCount(10).build());
	}

	@EventHandler
	public void onEntityBreed(EntityBreedEvent ebevent) {
		Entity entity = ebevent.getEntity();

		pFactory.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld())
				.setColors(ParticleColor.IVORY, ParticleColor.MAGENTA).build());
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent edevent) {
		Entity entity = edevent.getEntity();

		pFactory.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation().add(0.0, 1.0, 0.0), entity.getWorld()).setCount(25)
				.setColors(ParticleColor.RED).build());
	}
}
