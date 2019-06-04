package com.nordryd.event.entity.player;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Config;
import com.nordryd.enums.Metadata;
import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleDragonBreath;
import com.nordryd.particle.ParticleFactory;
import com.nordryd.particle.ParticleItemCrack;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.IUtility;
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
	 *            {@code JavaPlugin}
	 */
	public PlayerEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}

	/**
	 * Handler for when a {@link Player} joins the game.
	 * 
	 * @param pjevent
	 *            {@link PlayerJoinEvent}
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();

		if (jPlugin.getConfig().getBoolean(Config.DO_CUSTOM_JOIN_MESSAGES.getKey())) {
			List<String> possibleMessages = jPlugin.getConfig().getStringList(Config.LOGIN_MESSAGES.getKey());

			pjevent.setJoinMessage(possibleMessages.get(rng.nextInt(possibleMessages.size())).replace(IValues.PLAYER_NAME_ESCAPE, player.getName()));
		}

		player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, false));

		checkPlayerHealth(player);

		ParticleFactory.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	/**
	 * Handler for when a {@link Player} leaves the game.
	 * 
	 * @param pqevent
	 *            {@link PlayerQuitEvent}
	 */
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent pqevent) {
		Player player = pqevent.getPlayer();

		if (jPlugin.getConfig().getBoolean(Config.DO_CUSTOM_LEAVE_MESSAGES.getKey())) {
			List<String> possibleMessages = jPlugin.getConfig().getStringList(Config.LOGOUT_MESSAGES.getKey());

			pqevent.setQuitMessage(possibleMessages.get(rng.nextInt(possibleMessages.size())).replace(IValues.PLAYER_NAME_ESCAPE, player.getName()));
		}
	}

	/**
	 * Handler for when a {@link Player} respawns.
	 * 
	 * @param prevent
	 *            {@link PlayerRespawnEvent}
	 */
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent prevent) {
		Player player = prevent.getPlayer();

		ParticleFactory.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	/**
	 * Handler for when a {@link Player} dings.
	 * 
	 * @param plcevent
	 *            {@link PlayerLevelChangeEvent}
	 */
	@EventHandler
	public void onDing(PlayerLevelChangeEvent plcevent) {
		if (jPlugin.getConfig().getBoolean(Config.DO_DING_PARTICLES.getKey())) {
			Player player = plcevent.getPlayer();

			if (plcevent.getNewLevel() > plcevent.getOldLevel()) {
				float pitch = 1.0f;
				player.sendMessage(ChatColor.AQUA + "Ding! You are now level " + ChatColor.GREEN + ChatColor.BOLD + plcevent.getNewLevel());
				ParticleFactory.spawnParticles(
						ParticleDragonBreath.getBuilder(player.getLocation().add(0, 1.25, 0), player.getWorld()).setCount(100).build(),
						ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1.25, 0), player.getWorld()).setColors(ParticleColor.values())
								.setCount(100).build());
				player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch);
				player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch + (pitch * 0.5f));
				player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch + (pitch * 1.5f) + (pitch * 1.75f));
			}
		}
	}

	/**
	 * Handler for when the {@link Player} damages an {@link Entity}.
	 * 
	 * @param edbeevent
	 *            {@link EntityDamageByEntityEvent}
	 */
	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent edbeevent) {
		if (edbeevent.getDamager().getType().equals(EntityType.PLAYER) && (edbeevent.getEntity() instanceof LivingEntity)) {
			LivingEntity entity = (LivingEntity) edbeevent.getEntity();

			if (jPlugin.getConfig().getBoolean(Config.DO_MOB_GORE_PARTICLES.getKey())) {
				ParticleFactory.spawnParticles(ParticleItemCrack
						.getBuilder(entity.getLocation().add(0, entity.getHeight() * 0.75, 0), entity.getWorld(), IUtility.getBloodColor(entity))
						.setCount(50).build());

				ItemStack tool = ((Player) edbeevent.getDamager()).getInventory().getItemInMainHand();

				if (jPlugin.getConfig().getBoolean(Config.DO_ENCHANTED_WEAPON_PARTICLES.getKey()) && IUtility.isTool(tool.getType())
						&& (!tool.getEnchantments().isEmpty())) {
					ParticleFactory
							.spawnParticles(ParticleSparkle.getBuilder(entity.getLocation().add(0, 1, 0), entity.getWorld()).setCount(3).build());
				}
			}
		}
	}

	/**
	 * Handler for when the {@link Player} shoots a bow.
	 * 
	 * @param esbevent
	 *            {@link EntityShootBowEvent}
	 */
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent esbevent) {
		if (esbevent.getEntity().getType().equals(EntityType.PLAYER)) {
			Entity arrow = esbevent.getProjectile(), shooter = esbevent.getEntity();

			if (!((Player) shooter).getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
				arrow.setMetadata(Metadata.FIRED_FROM_ENCHANTED_WEAPON.getKey(), new FixedMetadataValue(jPlugin, 0));
			}
		}
	}

	/**
	 * Handler for when the {@link Player} is hit and takes damage.
	 * 
	 * @param edevent
	 *            {@link EntityDamageEvent}
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
	 *            {@link EntityRegainHealthEvent}
	 */
	@EventHandler
	public void onPlayerRegainHealth(EntityRegainHealthEvent erhevent) {
		if (jPlugin.getConfig().getBoolean(Config.DO_LOW_HEALTH_EFFECTS.getKey()) && erhevent.getEntityType().equals(EntityType.PLAYER)) {
			checkPlayerHealth((Player) erhevent.getEntity());
		}
	}

	/**
	 * Handler for when the {@link Player} empties a bucket.
	 * 
	 * @param pbeevent
	 *            {@link PlayerBucketEmptyEvent}
	 */
	@EventHandler
	public void onBucketEmpty(PlayerBucketEmptyEvent pbeevent) {
		Player player = pbeevent.getPlayer();
		Block block = pbeevent.getBlockClicked();
		if (pbeevent.getBucket().equals(Material.WATER_BUCKET)) {
			ParticleFactory.spawnParticles(ParticleItemCrack
					.getBuilder(IUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld(), new ItemStack(Material.LAPIS_BLOCK))
					.setCount(15).build());
		}
		else if (pbeevent.getBucket().equals(Material.LAVA_BUCKET)) {
			ParticleFactory.spawnParticles(ParticleItemCrack
					.getBuilder(IUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld(), new ItemStack(Material.ORANGE_WOOL))
					.setCount(15).build());
		}
		else if (pbeevent.getBucket().equals(Material.MILK_BUCKET)) {
			ParticleFactory.spawnParticles(ParticleItemCrack
					.getBuilder(player.getLocation().add(0, 1.75, 0), block.getWorld(), new ItemStack(Material.WHITE_WOOL)).setCount(15).build());
		}
	}

	private void checkPlayerHealth(Player player) {
		if (!player.getMetadata(Metadata.PLAYER_HEALTH_LOW.getKey()).get(0).asBoolean() && (player.getHealth() <= IValues.LOW_HEALTH_THRESHOLD)) {
			player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, true));
			player.sendMessage(ChatColor.RED + "[WARNING] " + ChatColor.DARK_RED + "Health is low!");
			player.playSound(player.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, 1.0f, IValues.PITCH[1]);
			ParticleFactory.spawnParticles(ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1, 0), player.getWorld())
					.setColors(ParticleColor.RED, ParticleColor.FIREBRICK, ParticleColor.ORANGE).setCount(50).build());
		}
		else if (player.getMetadata(Metadata.PLAYER_HEALTH_LOW.getKey()).get(0).asBoolean() && (player.getHealth() > IValues.LOW_HEALTH_THRESHOLD)) {
			player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, false));
		}
	}
}
