package com.nordryd.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Fox;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Slime;
import org.bukkit.entity.WaterMob;
import org.bukkit.entity.Wolf;

/**
 * <p>
 * Interface for miscellaneous utility functions.
 * </p>
 * 
 * @author Nordryd
 */
public interface PluginUtility
{
	/**
	 * <b>True</b> if the material given is that of a tool.
	 * 
	 * @param material
	 *            {@code Material}
	 * @return If the given material is a tool or not.
	 */
	public static boolean isTool(Material material) {
		for (String materialString : Values.TOOLS) {
			if (material.toString().contains(materialString)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Centers the location in the actual geometric center of the given block's
	 * location, rather than its logical origin.
	 * 
	 * @param location
	 *            {@code Location} of the block.
	 * @return The {@code Location} geometric center of the given block.
	 */
	public static Location getCenteredBlockLocation(Location location) {
		return location.add(Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET, Values.BLOCK_CENTER_OFFSET);
	}

	/**
	 * Determines if a living entity is able to attack or not. This stops docile
	 * mobs from having unnecessary particles.
	 * 
	 * @param entity
	 *            {@code LivingEntity} to check.
	 * @return If the {@code LivingEntity} can attack or not.
	 */
	public static boolean canAttack(LivingEntity entity) {
		if (!((entity instanceof Creature) || (entity instanceof Flying) || (entity instanceof Slime))) {
			return false;
		}

		if (entity instanceof WaterMob) {
			return false;
		}

		if (entity instanceof NPC) {
			return false;
		}

		if (!((entity instanceof PolarBear) || (entity instanceof Fox) || (entity instanceof Wolf) || (entity instanceof Cat))) {
			return false;
		}

		return true;
	}
}
