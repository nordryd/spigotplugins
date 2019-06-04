package com.nordryd.event.block;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SpongeAbsorbEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Config;
import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.event.entity.player.PlayerEventListener;
import com.nordryd.particle.ParticleFactory;
import com.nordryd.particle.ParticleItemCrack;
import com.nordryd.particle.ParticleSparkle;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.IUtility;

/**
 * <p>
 * Class to handle all {@link EntityEvent}s. {@link EntityEvent}s that pertain
 * specifically to players will be handled in {@link PlayerEventListener}.
 * </p>
 * 
 * @author Nordryd
 */
public class BlockEventListener extends EventListener
{
	/**
	 * Constructor.
	 * 
	 * @param plugin
	 *        {@link JavaPlugin}
	 */
	public BlockEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}

	/**
	 * Handler for when a leaf block decays.
	 * 
	 * @param ldevent
	 *        {@link LeavesDecayEvent}
	 */
	@EventHandler
	public void onLeafDecay(LeavesDecayEvent ldevent) {
		if (jPlugin.getConfig().getBoolean(Config.DO_LEAF_DECAY_PARTICLES.getKey())) {
			Block leaf = ldevent.getBlock();

			ParticleFactory.spawnParticles(ParticleSpellEffect.getBuilder(leaf.getLocation(), leaf.getWorld())
					.setColors(ParticleColor.GREEN, ParticleColor.SEA_GREEN, ParticleColor.SPRING_GREEN).build());
		}
	}

	/**
	 * Handler for when a redstone block becomes active.
	 * 
	 * @param brevent
	 *        {@link BlockRedstoneEvent}
	@EventHandler
	public void onRedstone(BlockRedstoneEvent brevent) {
		if (jPlugin.getConfig().getBoolean(Config.DO_REDSTONE_PARTICLES.getKey()) && (brevent.getOldCurrent() == 0)) {
			Block block = brevent.getBlock();
			ParticleFactory.spawnParticles(ParticleSpellEffect
					.getBuilder(IUtility.getCenteredBlockLocation(block.getLocation()).subtract(0, IValues.BLOCK_CENTER_OFFSET, 0), block.getWorld())
					.setColors(ParticleColor.RED, ParticleColor.FIREBRICK).setCount(1).build());
		}
	}

	/**
	 * Handler for when a sponge absorbs water.
	 * 
	 * @param saevent
	 *        {@link SpongeAbsorbEvent}
	 */
	@EventHandler
	public void onSpongeAbsorb(SpongeAbsorbEvent saevent) {
		Block sponge = saevent.getBlock();

		ParticleFactory.spawnParticles(ParticleItemCrack
				.getBuilder(IUtility.getCenteredBlockLocation(sponge.getLocation()), sponge.getWorld(), new ItemStack(Material.LAPIS_BLOCK))
				.setCount(20).build());
	}

	/**
	 * Handler when a block breaks.
	 * 
	 * @param bbevent
	 *        {@link BlockBreakEvent}.
	 */
	@EventHandler
	public void onBlockBreak(BlockBreakEvent bbevent) {
		if (jPlugin.getConfig().getBoolean(Config.DO_ORE_BREAK_PARTICLES.getKey())) {
			Block block = bbevent.getBlock();
			Player player = bbevent.getPlayer();
			if (block.getType().toString().contains("_ORE")) {
				List<ParticleColor> colors = new ArrayList<>();
				if (block.getType().equals(Material.COAL_ORE)) {
					colors.add(ParticleColor.BLACK);
					colors.add(ParticleColor.GRAY);
				}
				else if (block.getType().equals(Material.DIAMOND_ORE)) {
					colors.add(ParticleColor.LIGHT_SKY_BLUE);
					colors.add(ParticleColor.LIGHT_BLUE);
				}
				else if (block.getType().equals(Material.EMERALD_ORE)) {
					colors.add(ParticleColor.SPRING_GREEN);
					colors.add(ParticleColor.SEA_GREEN);
				}
				else if (block.getType().equals(Material.GOLD_ORE)) {
					colors.add(ParticleColor.GOLD);
					colors.add(ParticleColor.LIGHT_GOLDENROD);
				}
				else if (block.getType().equals(Material.IRON_ORE)) {
					colors.add(ParticleColor.LAVENDAR);
					colors.add(ParticleColor.CORNSILK);
				}
				else if (block.getType().equals(Material.LAPIS_ORE)) {
					colors.add(ParticleColor.BLUE);
					colors.add(ParticleColor.ROYAL_BLUE);
				}
				else if (block.getType().equals(Material.NETHER_QUARTZ_ORE)) {
					colors.add(ParticleColor.WHITE);
					colors.add(ParticleColor.FIREBRICK);
				}
				else {
					colors.add(ParticleColor.RED);
					colors.add(ParticleColor.TOMATO);
				}
				ParticleFactory.spawnParticles(ParticleSpellEffect
						.getBuilder(IUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld()).setColors(colors).setCount(50).build());
			}

			if (!player.getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
				ParticleFactory.spawnParticles(
						ParticleSparkle.getBuilder(IUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld()).setCount(5).build());
			}
		}
	}
}