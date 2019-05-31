package com.nordryd.event.entity.player;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Metadata;
import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleDragonBreath;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.Broadcaster;
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
	 * @param plugin
	 *            {@code JavaPlugin}
	 */
	public PlayerEventListener(JavaPlugin plugin) {
		super(plugin);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();

		pjevent.setJoinMessage(ChatColor.AQUA + "Who invited " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + "?");
		player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, false));
		calculateIfPlayerHasLowHealth(player);

		pHandler.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent prevent) {
		Player player = prevent.getPlayer();

		pHandler.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	@EventHandler
	public void onPlayerItemBreak(PlayerItemBreakEvent pibevent) {
		Player player = pibevent.getPlayer();

		pHandler.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(10).build());
	}

	@EventHandler
	public void onDing(PlayerLevelChangeEvent plcevent) {
		Player player = plcevent.getPlayer();

		if (plcevent.getNewLevel() > plcevent.getOldLevel()) {
			float pitch = 1.0f;
			Broadcaster.sendMessage(player, "Ding! You are now level: " + ChatColor.GREEN + ChatColor.BOLD + plcevent.getNewLevel());
			pHandler.spawnParticles(ParticleDragonBreath.getBuilder(player.getLocation().add(0, 1.25, 0), player.getWorld()).setCount(100).build(),
					ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1.25, 0), player.getWorld()).setColors(ParticleColor.values())
							.setCount(100).build());
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch);
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch + (pitch * 0.5f));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch + (pitch * 1.5f) + (pitch * 1.75f));
		}
	}

	@EventHandler
	public void onPlayerDamagedBelowLowHealthThreshold(EntityDamageEvent edevent) {
		if (edevent.getEntityType().equals(EntityType.PLAYER)) {
			calculateIfPlayerHasLowHealth((Player) edevent.getEntity());
		}
	}

	@EventHandler
	public void onPlayerHealingAboveLowHealthThreshold(EntityRegainHealthEvent erhevent) {
		if (erhevent.getEntityType().equals(EntityType.PLAYER)) {
			calculateIfPlayerHasLowHealth((Player) erhevent.getEntity());
		}
	}

	private void calculateIfPlayerHasLowHealth(Player player) {
		if (!player.getMetadata(Metadata.PLAYER_HEALTH_LOW.getKey()).get(0).asBoolean() && (player.getHealth() <= Values.LOW_HEALTH_THRESHOLD)) {
			player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, true));
			player.sendMessage(ChatColor.RED + "[WARNING] " + ChatColor.DARK_RED + "Health is low!");
			player.playSound(player.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, 1.0f, Values.PITCH[1]);
			pHandler.spawnParticles(ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1, 0), player.getWorld())
					.setColors(ParticleColor.RED, ParticleColor.FIREBRICK, ParticleColor.ORANGE).setCount(50).build());
		}
		else if (player.getMetadata(Metadata.PLAYER_HEALTH_LOW.getKey()).get(0).asBoolean() && (player.getHealth() > Values.LOW_HEALTH_THRESHOLD)) {
			player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, false));
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent pievent) {
		Action action = pievent.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK) {
			Block block = pievent.getClickedBlock();
			if (Values.SIGNS.contains(block.getType())) {
				PlayerInteractEventHandler.onSignInteract(pievent.getPlayer(), ((Sign) block.getState()));
			}
		}
	}
}
