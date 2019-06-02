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
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleDust;
import com.nordryd.particle.ParticleItemCrack;
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
public class BlockEventListener extends EventListener
{
	/**
	 * Constructor.
	 * 
	 * @param plugin
	 *            {@code JavaPlugin}
	 */
	public BlockEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}

	@EventHandler
	public void onLeafDecay(LeavesDecayEvent ldevent) {
		Block leaf = ldevent.getBlock();

		pFactory.spawnParticles(ParticleDust.getBuilder(leaf.getLocation(), leaf.getWorld()).setColor(ParticleColor.GREEN).setSize(3).build());
	}

	@EventHandler
	public void onRedstone(BlockRedstoneEvent brevent) {
		if (brevent.getOldCurrent() == 0) {
			Block block = brevent.getBlock();
			pFactory.spawnParticles(ParticleSpellEffect
					.getBuilder(PluginUtility.getCenteredBlockLocation(block.getLocation()).subtract(0, Values.BLOCK_CENTER_OFFSET, 0), block.getWorld())
					.setColors(ParticleColor.RED, ParticleColor.FIREBRICK).setCount(1).build());
		}
	}

	@EventHandler
	public void onSpongeAbsorb(SpongeAbsorbEvent saevent) {
		Block sponge = saevent.getBlock();

		pFactory.spawnParticles(ParticleItemCrack
				.getBuilder(PluginUtility.getCenteredBlockLocation(sponge.getLocation()), sponge.getWorld(), new ItemStack(Material.LAPIS_BLOCK))
				.setCount(20).build());
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent bbevent) {
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
			pFactory.spawnParticles(
					ParticleDust.getBuilder(PluginUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld()).setColor(colors.get(0))
							.setSize(3).build(),
					ParticleSpellEffect.getBuilder(PluginUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld()).setColors(colors)
							.setCount(40).build());
		}

		if (!player.getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
			pFactory.spawnParticles(
					ParticleSparkle.getBuilder(PluginUtility.getCenteredBlockLocation(block.getLocation()), block.getWorld()).setCount(5).build());
		}
	}
}