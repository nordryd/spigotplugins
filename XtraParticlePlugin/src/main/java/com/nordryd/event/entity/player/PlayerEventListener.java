package com.nordryd.event.entity.player;

import java.util.List;
import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import com.nordryd.enums.Config;
import com.nordryd.enums.Metadata;
import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleDragonBreath;
import com.nordryd.particle.ParticleItemCrack;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.PluginUtility;
import com.nordryd.util.Values;

/**
 * <p>
 * Class to handle all {@code PlayerEvent}s, or events that specifically involve
 * players (like {@code EntityEvent}s that are only handled for
 * {@code EntityType.PLAYER}.
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

	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();

		if (jPlugin.getConfig().getBoolean(Config.DO_CUSTOM_LOGIN_MESSAGES.getKey())) {
			List<String> possibleMessages = jPlugin.getConfig().getStringList(Config.LOGIN_MESSAGES.getKey());

			pjevent.setJoinMessage(possibleMessages.get(rng.nextInt(possibleMessages.size())).replace(Values.PLAYER_NAME_ESCAPE, player.getName()));
		}

		player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, false));

		checkPlayerHealth(player);

		pFactory.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent pqevent) {
		Player player = pqevent.getPlayer();

		if (jPlugin.getConfig().getBoolean(Config.DO_CUSTOM_LOGOUT_MESSAGES.getKey())) {
			List<String> possibleMessages = jPlugin.getConfig().getStringList(Config.LOGOUT_MESSAGES.getKey());

			pqevent.setQuitMessage(possibleMessages.get(rng.nextInt(possibleMessages.size())).replace(Values.PLAYER_NAME_ESCAPE, player.getName()));
		}
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent prevent) {
		Player player = prevent.getPlayer();

		pFactory.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	@EventHandler
	public void onPlayerItemBreak(PlayerItemBreakEvent pibevent) {
		Player player = pibevent.getPlayer();

		pFactory.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(10).build());
	}

	@EventHandler
	public void onDing(PlayerLevelChangeEvent plcevent) {
		Player player = plcevent.getPlayer();

		if (plcevent.getNewLevel() > plcevent.getOldLevel()) {
			float pitch = 1.0f;
			player.sendMessage(ChatColor.AQUA + "Ding! You are now level " + ChatColor.GREEN + ChatColor.BOLD + plcevent.getNewLevel());
			pFactory.spawnParticles(ParticleDragonBreath.getBuilder(player.getLocation().add(0, 1.25, 0), player.getWorld()).setCount(100).build(),
					ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1.25, 0), player.getWorld()).setColors(ParticleColor.values())
							.setCount(100).build());
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch);
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch + (pitch * 0.5f));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch + (pitch * 1.5f) + (pitch * 1.75f));
		}
	}

	@EventHandler
	public void onBuffDebuffGain(EntityPotionEffectEvent epeevent) {
		if (epeevent.getEntityType().equals(EntityType.PLAYER)) {
			Player player = (Player) epeevent.getEntity();
			Optional<PotionEffect> activeEffect = Optional.ofNullable(epeevent.getNewEffect()),
					fadedEffect = Optional.ofNullable(epeevent.getOldEffect());

			if (activeEffect.isPresent() && (!player.getActivePotionEffects().contains(activeEffect.get()))) {
				((Player) epeevent.getEntity()).sendMessage(ChatColor.LIGHT_PURPLE + "[ACTIVE] " + ChatColor.DARK_PURPLE
						+ activeEffect.get().getType().getName().replaceAll("_", " ") + " " + (activeEffect.get().getAmplifier() + 1));
			}
			else if(fadedEffect.isPresent()) {
				((Player) epeevent.getEntity()).sendMessage(ChatColor.GRAY + "[FADED] " + ChatColor.DARK_GRAY
						+ fadedEffect.get().getType().getName().replaceAll("_", " ") + " " + (fadedEffect.get().getAmplifier() + 1));
			}
		}
	}

	@EventHandler
	public void onPlayerDamaged(EntityDamageEvent edevent) {
		if (edevent.getEntityType().equals(EntityType.PLAYER)) {
			checkPlayerHealth((Player) edevent.getEntity());
		}
	}

	@EventHandler
	public void onPlayerEnchantedToolAttack(EntityDamageByEntityEvent edbeevent) {
		if (edbeevent.getDamager().getType().equals(EntityType.PLAYER)) {
			ItemStack tool = ((Player) edbeevent.getDamager()).getInventory().getItemInMainHand();

			if (PluginUtility.isTool(tool.getType()) && (!tool.getEnchantments().isEmpty())) {
				Entity entity = edbeevent.getEntity();
				pFactory.spawnParticles(ParticleSparkle.getBuilder(entity.getLocation().add(0, 1, 0), entity.getWorld()).setCount(3).build());
			}
		}
	}

	@EventHandler
	public void onPlayerHealingAboveLowHealthThreshold(EntityRegainHealthEvent erhevent) {
		if (erhevent.getEntityType().equals(EntityType.PLAYER)) {
			checkPlayerHealth((Player) erhevent.getEntity());
		}
	}

	@EventHandler
	public void onBucketEmpty(PlayerBucketEmptyEvent pbeevent) {
		Player player = pbeevent.getPlayer();
		Block block = pbeevent.getBlockClicked();
		if (pbeevent.getBucket().equals(Material.WATER_BUCKET)) {
			pFactory.spawnParticles(ParticleItemCrack
					.getBuilder(PluginUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld(), new ItemStack(Material.LAPIS_BLOCK))
					.setCount(15).build());
		}
		else if (pbeevent.getBucket().equals(Material.LAVA_BUCKET)) {
			pFactory.spawnParticles(ParticleItemCrack
					.getBuilder(PluginUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld(), new ItemStack(Material.ORANGE_WOOL))
					.setCount(15).build());
		}
		else if (pbeevent.getBucket().equals(Material.MILK_BUCKET)) {
			pFactory.spawnParticles(ParticleItemCrack
					.getBuilder(player.getLocation().add(0, 1.75, 0), block.getWorld(), new ItemStack(Material.WHITE_WOOL)).setCount(15).build());
		}
	}

	@EventHandler
	public void onPlayerRiptide(PlayerRiptideEvent prevent) {
		Player player = prevent.getPlayer();

		pFactory.spawnParticles(ParticleItemCrack.getBuilder(player.getLocation().add(0, 1, 0), player.getWorld(), new ItemStack(Material.PRISMARINE))
				.setCount(15).build());
	}

	private void checkPlayerHealth(Player player) {
		if (!player.getMetadata(Metadata.PLAYER_HEALTH_LOW.getKey()).get(0).asBoolean() && (player.getHealth() <= Values.LOW_HEALTH_THRESHOLD)) {
			player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, true));
			player.sendMessage(ChatColor.RED + "[WARNING] " + ChatColor.DARK_RED + "Health is low!");
			player.playSound(player.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, 1.0f, Values.PITCH[1]);
			pFactory.spawnParticles(ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1, 0), player.getWorld())
					.setColors(ParticleColor.RED, ParticleColor.FIREBRICK, ParticleColor.ORANGE).setCount(50).build());
		}
		else if (player.getMetadata(Metadata.PLAYER_HEALTH_LOW.getKey()).get(0).asBoolean() && (player.getHealth() > Values.LOW_HEALTH_THRESHOLD)) {
			player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, false));
		}
	}
}
