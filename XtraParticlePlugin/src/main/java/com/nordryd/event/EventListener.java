package com.nordryd.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Metadata;
import com.nordryd.enums.ParticleColor;
import com.nordryd.particle.ParticleDragonBreath;
import com.nordryd.particle.ParticleDust;
import com.nordryd.particle.ParticleEnchanting;
import com.nordryd.particle.ParticleHandler;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.Broadcaster;
import com.nordryd.util.Values;

/**
 * <p>
 * Listener class for all events in the plugin.
 * </p>
 * <p>
 * TODO Consider separating events into different classes, such as
 * PlayerEventListener, EntityEventListener, and make this class the
 * superclass:<br>
 * {@code EventListener#getEventListeners(JavaPlugin)}; use this to initialize
 * all listeners within this class (and make the constructors private. facade
 * pattern?)
 * </p>
 * 
 * @author Nordryd
 */
public class EventListener implements Listener
{
	private final JavaPlugin plugin;
	private final ParticleHandler pHandler;

	/**
	 * Constructor.
	 * 
	 * @param pluginConfig
	 *            Plugin configuration.
	 */
	public EventListener(JavaPlugin plugin) {
		this.plugin = plugin;
		this.pHandler = new ParticleHandler(plugin.getConfig());
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();
		pjevent.setJoinMessage(ChatColor.AQUA + "Who invited " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + "?");
		player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(plugin, false));
		calculateIfPlayerHasLowHealth(player);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent pievent) {
		Action action = pievent.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK) {
			Block block = pievent.getClickedBlock();
			if (Values.SIGNS.contains(block.getType())) {
				PlayerInteractEvents.onSignInteract(pievent.getPlayer(), ((Sign) block.getState()));
			}
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
			player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(plugin, true));
			player.sendMessage(ChatColor.RED + "[WARNING] " + ChatColor.DARK_RED + "Health is low!");
			player.playSound(player.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, 1.0f, Values.PITCH[1]);
			pHandler.spawnParticles(ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1, 0), player.getWorld())
					.setColors(ParticleColor.RED, ParticleColor.FIREBRICK, ParticleColor.ORANGE).setCount(50).build());
		}
		else if (player.getMetadata(Metadata.PLAYER_HEALTH_LOW.getKey()).get(0).asBoolean() && (player.getHealth() > Values.LOW_HEALTH_THRESHOLD)) {
			player.setMetadata(Metadata.PLAYER_HEALTH_LOW.getKey(), Metadata.getMetadataValue(plugin, false));
		}
	}

	@EventHandler
	public void onDing(PlayerLevelChangeEvent plcevent) {
		Player player = plcevent.getPlayer();

		if (plcevent.getNewLevel() > plcevent.getOldLevel()) {
			float pitch = 1.0f;
			Broadcaster.sendMessage(player, "Ding! You are now level: " + ChatColor.GREEN + ChatColor.BOLD + plcevent.getNewLevel());
			pHandler.spawnParticles(ParticleDragonBreath.getBuilder(player.getLocation().add(0, 1, 0), player.getWorld()).setCount(100).build(),
					ParticleSpellEffect.getBuilder(player.getLocation().add(0, 1, 0), player.getWorld()).setColors(ParticleColor.values())
							.setCount(100).build());
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch);
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch + (pitch * 0.5f));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, pitch + (pitch * 1.5f) + (pitch * 1.75f));
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();

		pHandler.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent prevent) {
		Player player = prevent.getPlayer();

		pHandler.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(50).build());
	}

	@EventHandler
	public void onAdvancementDone(PlayerAdvancementDoneEvent paaevent) {
		Player player = paaevent.getPlayer();
		Advancement advancement = paaevent.getAdvancement();

		if (!advancement.getKey().toString().contains("minecraft:recipes")) {
			pHandler.spawnParticles(
					ParticleSpellEffect.getBuilder(player.getLocation(), player.getWorld()).setCount(50).setColors(ParticleColor.MAROON).build());
		}
	}

	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent esbevent) {
		Entity arrow = esbevent.getProjectile(), shooter = esbevent.getEntity();

		if (shooter.getType().equals(EntityType.PLAYER)) {
			if (!((Player) shooter).getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
				arrow.setMetadata(Metadata.FIRED_FROM_ENCHANTED_WEAPON.getKey(), new FixedMetadataValue(plugin, 0));
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
	public void onEntityDeath(EntityDeathEvent edevent) {
		Entity entity = edevent.getEntity();

		pHandler.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation().add(0.0, 1.0, 0.0), entity.getWorld()).setCount(25)
				.setColors(ParticleColor.RED).build());
	}

	@EventHandler
	public void onLeafDecay(LeavesDecayEvent ldevent) {
		Block leaf = ldevent.getBlock();

		pHandler.spawnParticles(ParticleDust.getBuilder(leaf.getLocation(), leaf.getWorld()).setColor(ParticleColor.GREEN).build());
	}

	@EventHandler
	public void onItemEnchanted(EnchantItemEvent eievent) {
		Player player = eievent.getEnchanter();
		Block enchantingTable = eievent.getEnchantBlock();

		pHandler.spawnParticles(ParticleEnchanting.getBuilder(player.getLocation().add(0.0, 1.0, 0.0), player.getWorld()).setCount(75).build(),
				ParticleEnchanting.getBuilder(
						enchantingTable.getLocation().add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET + 1, Values.BLOCK_CENTER_OFFSET),
						enchantingTable.getWorld()).setCount(75).build(),
				ParticleSpellEffect.getBuilder(
						enchantingTable.getLocation().add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET + 1, Values.BLOCK_CENTER_OFFSET),
						enchantingTable.getWorld()).setColors(ParticleColor.values()).setCount(75).build());
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
	public void onPlayerItemBreak(PlayerItemBreakEvent pibevent) {
		Player player = pibevent.getPlayer();

		pHandler.spawnParticles(ParticleSparkle.getBuilder(player.getLocation(), player.getWorld()).setCount(10).build());
	}

	@EventHandler
	public void onItemCraft(CraftItemEvent cievent) {
		Entity player = cievent.getWhoClicked();

		pHandler.spawnParticles(ParticleSpellEffect.getBuilder(player.getLocation().add(0.0, 1.0, 0.0), player.getWorld())
				.setColors(ParticleColor.RED_VIOLET).setCount(20).build());
	}

	@EventHandler
	public void onFurnaceSmeltSuccessful(FurnaceSmeltEvent fsevent) {
		Block furnace = fsevent.getBlock();

		pHandler.spawnParticles(ParticleSpellEffect
				.getBuilder(furnace.getLocation().add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET),
						furnace.getWorld())
				.setColors(ParticleColor.ORANGE).build());
	}

	@EventHandler
	public void onEntityBreed(EntityBreedEvent ebevent) {
		Entity entity = ebevent.getEntity();

		pHandler.spawnParticles(ParticleSpellEffect.getBuilder(entity.getLocation(), entity.getWorld())
				.setColors(ParticleColor.IVORY, ParticleColor.MAGENTA).build());
	}

	@EventHandler
	public void onBrew(BrewEvent bevent) {
		Block brewingStand = bevent.getBlock();

		pHandler.spawnParticles(ParticleSpellEffect.getBuilder(brewingStand.getLocation(), brewingStand.getWorld())
				.setColors(ParticleColor.RED_VIOLET, ParticleColor.LAVENDAR, ParticleColor.VIOLET).build());
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent bbevent) {
		Block block = bbevent.getBlock();
		if (Values.ORES.contains(block.getType())) {
			List<ParticleColor> colors = new ArrayList<>();
			if (block.getType().equals(Material.COAL_ORE)) {
				colors.add(ParticleColor.BLACK);
			}
			else if (block.getType().equals(Material.DIAMOND_ORE)) {
				colors.add(ParticleColor.CYAN);
				colors.add(ParticleColor.DARK_TURQUOISE);
			}
			else if (block.getType().equals(Material.EMERALD_ORE)) {
				colors.add(ParticleColor.LIME_GREEN);
			}
			else if (block.getType().equals(Material.GOLD_ORE)) {
				colors.add(ParticleColor.GOLD);
			}
			else if (block.getType().equals(Material.IRON_ORE)) {
				colors.add(ParticleColor.LAVENDAR);
			}
			else if (block.getType().equals(Material.LAPIS_ORE)) {
				colors.add(ParticleColor.BLUE);
			}
			else if (block.getType().equals(Material.NETHER_QUARTZ_ORE)) {
				colors.add(ParticleColor.IVORY);
				colors.add(ParticleColor.FIREBRICK);
			}
			else {
				colors.add(ParticleColor.RED);
			}
			pHandler.spawnParticles(
					ParticleDust
							.getBuilder(block.getLocation().add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET),
									block.getWorld())
							.setColor(colors.get(0)).setSize(3).build(),
					ParticleSpellEffect
							.getBuilder(block.getLocation().add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET),
									block.getWorld())
							.setColors((ParticleColor[]) colors.toArray()).setCount(40).build());
		}
	}
}
