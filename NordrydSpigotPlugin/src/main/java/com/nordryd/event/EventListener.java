package com.nordryd.event;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.LightningStrikeEvent;

import com.nordryd.util.Values;
import com.nordryd.util.Values.Config;
import com.nordryd.util.Values.Time;

/**
 * <p>
 * Listener class for all events in the mod.
 * </p>
 * 
 * @author Nordryd
 */
public class EventListener implements Listener
{
	private final FileConfiguration config;
	private final Random rand;

	private int playersSleeping = 0;

	/**
	 * Constructor.
	 * 
	 * @param pluginConfig
	 *            Plugin configuration.
	 */
	public EventListener(FileConfiguration pluginConfig) {
		this.config = pluginConfig;
		this.rand = new Random();
	}

	/**
	 * <p>
	 * Event when a player joins the game.
	 * </p>
	 * 
	 * @param pjevent
	 *            PlayerJoinEvent
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();
		Location location = player.getLocation();

		Bukkit.broadcastMessage(
				player.getName() + " has appeared at (" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + ")!");
	}

	/**
	 * <p>
	 * Event when a player chucks an egg.
	 * </p>
	 * 
	 * @param petevent
	 *            PlayerEggThrowEvent
	 */
	@EventHandler
	public void onPlayerEggThrow(PlayerEggThrowEvent petevent) {
		Egg egg = petevent.getEgg();
		World world = egg.getWorld();

		world.strikeLightning(egg.getLocation());
	}

	/**
	 * <p>
	 * Event when an entity dies.
	 * </p>
	 * 
	 * @param edevent
	 *            EntityDeathEvent
	 */
	@EventHandler
	public void onEntityDeath(EntityDeathEvent edevent) {
		Entity entity = edevent.getEntity();
		World world = entity.getWorld();

		world.spawnParticle(Particle.FIREWORKS_SPARK, entity.getLocation(), 10);
		world.playSound(entity.getLocation(), Sound.AMBIENT_UNDERWATER_ENTER, 3.0f, Values.PITCH[rand.nextInt(Values.PITCH.length)]);
	}

	/**
	 * Event when a player sleeps.
	 * 
	 * @param pbeevent
	 *            PlayerBedEnterEvent
	 */
	@EventHandler
	public void onPlayerSleep(PlayerBedEnterEvent pbeevent) {
		Player player = pbeevent.getPlayer();
		World world = player.getWorld();

		if (world.getTime() >= Time.NIGHT) {
			playersSleeping++;

			if (playersSleeping >= config.getInt(Config.PLAYERS_SLEEPING_TO_SKIP_NIGHT)) {
				Bukkit.broadcastMessage(config.getInt(Config.PLAYERS_SLEEPING_TO_SKIP_NIGHT) + " are sleeping! Skipping to day!");
				world.setTime(Time.DAY);
				pbeevent.setCancelled(true);
				playersSleeping = 0;
			}
			else {
				Bukkit.broadcastMessage(playersSleeping + " are sleeping. " + (config.getInt(Config.PLAYERS_SLEEPING_TO_SKIP_NIGHT) - playersSleeping)
						+ " needed to skip night.");
			}
		}
	}

	/**
	 * Event when a player awakes.
	 * 
	 * @param pblevent
	 *            PlayerBedLeaveEvent
	 */
	@EventHandler
	public void onPlayerAwake(PlayerBedLeaveEvent pblevent) {
		if (pblevent.getPlayer().getWorld().getTime() >= Time.NIGHT) {
			playersSleeping--;
			Bukkit.broadcastMessage(playersSleeping + " are sleeping. " + (config.getInt(Config.PLAYERS_SLEEPING_TO_SKIP_NIGHT) - playersSleeping)
					+ " needed to skip night.");
		}
	}

	@EventHandler
	public void onLeafDecay(LeavesDecayEvent ldevent) {
		Block block = ldevent.getBlock();
		block.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, block.getLocation(), 10);
	}

	/**
	 * <p>
	 * Event when lightning strikes. Might be modified later to include permissions
	 * to select who gets these notifications, as they can get rather annoying.
	 * </p>
	 * 
	 * @param lsevent
	 *            LightningStrikeEvent
	 */
	// @EventHandler
	public void onLightningStrike(LightningStrikeEvent lsevent) {
		LightningStrike lightning = lsevent.getLightning();
		Location location = lightning.getLocation();
		List<Entity> struckEntities = lightning.getNearbyEntities(Values.LIGHTNING_STRIKE_RADIUS, Values.LIGHTNING_STRIKE_RADIUS,
				Values.LIGHTNING_STRIKE_RADIUS);

		StringBuilder stringBuilder = new StringBuilder(
				"Lightning strike at (" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());

		if (!struckEntities.isEmpty()) {
			stringBuilder.append(", striking ");
			for (Entity entity : struckEntities) {
				stringBuilder.append(entity.getName() + ", ");
			}
			stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "");
		}

		Bukkit.broadcastMessage(stringBuilder.append('!').toString());
	}

	/**
	 * <p>
	 * Event when a player interacts with a beacon block, and the consequences that
	 * arise from trying to destroy it.
	 * </p>
	 * <p>
	 * May try to implement {@code LightningDoomThread} for a slower, more fearful
	 * death.
	 * </p>
	 * 
	 * @param pievent
	 *            PlayerInteractEvent
	 */
	@EventHandler
	public void onPlayerInteractWithBeaconBlock(PlayerInteractEvent pievent) {
		Action action = pievent.getAction();
		Block block = pievent.getClickedBlock();

		if (action.equals(Action.LEFT_CLICK_BLOCK) && block.getType().equals(Material.BEACON)) {
			Player player = pievent.getPlayer();
			int locationX = player.getLocation().getBlockX(), locationZ = player.getLocation().getBlockZ(), radius = Values.BEACON_SIN_RADIUS;
			World world = player.getWorld();
			Bukkit.broadcastMessage("DO NOT TOUCH THE BACON, " + player.getName());

			for (int x = locationX - radius; x < locationX + radius; x++) {
				for (int z = locationZ - radius; z < locationZ + radius; z++) {
					Location location = new Location(world, x, player.getLocation().getY(), z);
					world.strikeLightning(location);
					Block groundBlock = location.add(0, -1, 0).getBlock();
					if (!(groundBlock.getType().equals(Material.AIR) || groundBlock.getType().equals(Material.NETHERRACK))) {
						groundBlock.setType(Material.NETHERRACK);
					}
				}
			}
		}
	}
}
