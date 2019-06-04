package com.nordryd.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Illager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.WaterMob;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;

/**
 * <p>
 * Interface for miscellaneous utility functions.
 * </p>
 * 
 * @author Nordryd
 */
public interface IUtility
{
	/**
	 * <b>True</b> if the material given is that of a tool.
	 * 
	 * @param material
	 *            {@link Material}
	 * @return If the given material is a tool or not.
	 */
	public static boolean isTool(Material material) {
		for (String materialString : IValues.TOOLS) {
			if (material.toString().contains(materialString)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Convert milliseconds into <i>Minecraft</i> game ticks (20 ticks per second).
	 * 
	 * @param ms
	 *            Time in <b>ms</b>
	 * @return Time in <i>Minecraft</i> ticks.
	 */
	public static long getTicksFromMillis(long ms) {
		return (ms * 1000) / 20;
	}

	/**
	 * Centers the location in the actual geometric center of the given block's
	 * location, rather than its logical origin.
	 * 
	 * @param location
	 *            {@link Location} of the block.
	 * @return The {@link Location} geometric center of the given block.
	 */
	public static Location getCenteredBlockLocation(Location location) {
		return location.add(IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET, IValues.BLOCK_CENTER_OFFSET);
	}

	/**
	 * Gives the blood color of the given living entity.
	 * 
	 * @param entity
	 *            {@link LivingEntity}
	 * @return The {@link ItemStack} with the correct color to act as the entity's
	 *         blood.
	 */
	public static ItemStack getBloodColor(LivingEntity entity) {
		if (entity.getType().toString().contains("ENDER") || entity.getType().equals(EntityType.SHULKER)) {
			return new ItemStack(Material.PURPLE_WOOL);
		}

		if ((entity instanceof Flying) || entity.getType().equals(EntityType.SNOWMAN)) {
			return new ItemStack(Material.WHITE_WOOL);
		}

		if (entity.getType().equals(EntityType.IRON_GOLEM)
				|| (entity.getType().toString().contains("SKELETON") && !(entity instanceof WitherSkeleton))) {
			return new ItemStack(Material.GRAY_WOOL);
		}

		if (entity.getType().equals(EntityType.VEX)) {
			return new ItemStack(Material.LIGHT_BLUE_WOOL);
		}

		if (entity instanceof Slime) {
			if (entity.getType().equals(EntityType.MAGMA_CUBE)) {
				return new ItemStack(Material.MAGMA_BLOCK);
			}

			return new ItemStack(Material.SLIME_BLOCK);
		}

		if (entity.getType().equals(EntityType.WITCH)) {
			return new ItemStack(Material.MAGENTA_WOOL);
		}

		if (entity.getType().toString().contains("WITHER") || entity.getType().equals(EntityType.SILVERFISH) || (entity instanceof Spider)
				|| entity.getType().equals(EntityType.SQUID)) {
			return new ItemStack(Material.BLACK_WOOL);
		}

		if ((entity instanceof Illager) || entity.getType().equals(EntityType.CREEPER)) {
			return new ItemStack(Material.LIME_WOOL);
		}

		return new ItemStack(Material.RED_WOOL);
	}

	/**
	 * Determines if a living entity is able to attack or not. This stops docile
	 * mobs from having unnecessary particles.
	 * 
	 * @param entity
	 *            {@link LivingEntity} to check.
	 * @return If the {@link LivingEntity} can attack or not.
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
