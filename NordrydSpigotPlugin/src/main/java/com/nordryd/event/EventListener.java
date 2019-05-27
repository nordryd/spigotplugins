package com.nordryd.event;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.LightningStrikeEvent;

import com.nordryd.util.Values;

/**
 * <p>
 * Listener class for all events in the mod.
 * </p>
 * 
 * @author Nordryd
 */
public class EventListener implements Listener
{
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

		world.createExplosion(entity.getLocation(), Values.NULL_EXPLOSION);
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
					if (!groundBlock.getType().equals(Material.AIR)) {
						groundBlock.setType(Material.NETHERRACK);
					}
				}
			}
		}
	}
}
