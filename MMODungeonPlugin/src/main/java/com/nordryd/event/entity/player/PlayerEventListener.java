package com.nordryd.event.entity.player;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Config;
import com.nordryd.enums.Metadata;
import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleFactory;
import com.nordryd.particle.ParticleSpellEffect;
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

		player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(jPlugin, false));

		checkPlayerHealth(player);
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
