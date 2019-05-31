package com.nordryd.event.block;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleDust;
import com.nordryd.particle.ParticleSpellEffect;
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

		pHandler.spawnParticles(ParticleDust.getBuilder(leaf.getLocation(), leaf.getWorld()).setColor(ParticleColor.GREEN).build());
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