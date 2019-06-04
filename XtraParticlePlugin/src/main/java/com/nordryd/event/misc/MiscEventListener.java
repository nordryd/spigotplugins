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
import com.nordryd.particle.ParticleFactory;
import com.nordryd.particle.ParticleSpellEffect;
import com.nordryd.util.IValues;

/**
 * <p>
 * Class to handle all miscellaneous {@link Event}s.
 * </p>
 * <p>
 * {@link EventHandler}s may be placed here until refactored to be grouped with
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
	 *            {@link JavaPlugin}
	 */
	public MiscEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}

	/**
	 * Handler for when an item is enchanted.
	 * 
	 * @param eievent
	 *            {@link EnchantItemEvent}
	 */
	@EventHandler
	public void onItemEnchanted(EnchantItemEvent eievent) {
		Player player = eievent.getEnchanter();
		Block enchantingTable = eievent.getEnchantBlock();

		ParticleFactory
				.spawnParticles(ParticleEnchanting.getBuilder(player.getLocation().add(0.0, 2.0, 0.0), player.getWorld()).setCount(75).build(),
						ParticleEnchanting.getBuilder(enchantingTable.getLocation().add(IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET + 1,
								IValues.BLOCK_CENTER_OFFSET), enchantingTable.getWorld()).setCount(75).build(),
						ParticleSpellEffect
								.getBuilder(enchantingTable.getLocation().add(IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET + 1,
										IValues.BLOCK_CENTER_OFFSET), enchantingTable.getWorld())
								.setColors(ParticleColor.values()).setCount(75).build());
	}

	/**
	 * Handler for when an item is crafted.
	 * 
	 * @param cievent
	 *            {@link CraftItemEvent}
	 */
	@EventHandler
	public void onItemCraft(CraftItemEvent cievent) {
		Entity player = cievent.getWhoClicked();

		ParticleFactory.spawnParticles(ParticleSpellEffect.getBuilder(player.getLocation().add(0.0, 1.0, 0.0), player.getWorld())
				.setColors(ParticleColor.RED_VIOLET).setCount(20).build());
	}

	/**
	 * Handler for when a furnace successfully smelts something.
	 * 
	 * @param fsevent
	 *            {@link FurnaceSmeltEvent}
	 */
	@EventHandler
	public void onFurnaceSmeltSuccessful(FurnaceSmeltEvent fsevent) {
		Block furnace = fsevent.getBlock();

		ParticleFactory.spawnParticles(ParticleSpellEffect
				.getBuilder(furnace.getLocation().add(IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET),
						furnace.getWorld())
				.setColors(ParticleColor.ORANGE).build());
	}

	/**
	 * Handler for when a potion is successfully brewed in a brewing stand.
	 * 
	 * @param bevent
	 *            {@link BrewEvent}
	 */
	@EventHandler
	public void onBrew(BrewEvent bevent) {
		Block brewingStand = bevent.getBlock();

		ParticleFactory.spawnParticles(ParticleSpellEffect.getBuilder(brewingStand.getLocation(), brewingStand.getWorld())
				.setColors(ParticleColor.RED_VIOLET, ParticleColor.LAVENDAR, ParticleColor.VIOLET).build());
	}
}
