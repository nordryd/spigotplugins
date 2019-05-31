package com.nordryd.event.misc;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ParticleColor;
import com.nordryd.event.EventListener;
import com.nordryd.particle.ParticleEnchanting;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.Values;

/**
 * <p>
 * Class to handle all miscellaneous {@code Event}s.
 * </p>
 * <p>
 * {@code EventHandler}s may be placed here until refactored to be grouped with
 * other methods.
 * </p>
 * 
 * @author Nordryd
 */
public class MiscEventListener extends EventListener
{
	/**
	 * Constructor.
	 * 
	 * @param plugin
	 *            {@code JavaPlugin}
	 */
	public MiscEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}

	@EventHandler
	public void onItemEnchanted(EnchantItemEvent eievent) {
		Player player = eievent.getEnchanter();
		Block enchantingTable = eievent.getEnchantBlock();

		pHandler.spawnParticles(ParticleEnchanting.getBuilder(player.getLocation().add(0.0, 2.0, 0.0), player.getWorld()).setCount(75).build(),
				ParticleEnchanting.getBuilder(
						enchantingTable.getLocation().add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET + 1, Values.BLOCK_CENTER_OFFSET),
						enchantingTable.getWorld()).setCount(75).build(),
				ParticleSpellEffect.getBuilder(
						enchantingTable.getLocation().add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET + 1, Values.BLOCK_CENTER_OFFSET),
						enchantingTable.getWorld()).setColors(ParticleColor.values()).setCount(75).build());
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
	public void onBrew(BrewEvent bevent) {
		Block brewingStand = bevent.getBlock();

		pHandler.spawnParticles(ParticleSpellEffect.getBuilder(brewingStand.getLocation(), brewingStand.getWorld())
				.setColors(ParticleColor.RED_VIOLET, ParticleColor.LAVENDAR, ParticleColor.VIOLET).build());
	}
}
