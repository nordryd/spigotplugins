package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import com.nordryd.util.IValues;

/**
 * <p>
 * Class for <b><i>dust</i></b> particles with certain characteristics. Based on
 * <b>ITEM_CRACK</b> particles based on the kind of {@link ItemStack} given.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleItemCrack extends AbstractParticle
{
	private final ItemStack item;

	private ParticleItemCrack(Location location, World world, int count, ItemStack item) {
		super(location, world, count);
		this.item = item;
	}

	/**
	 * Get a {@link Builder} to get an instance of {@link ParticleSpellEffect}.
	 * 
	 * @param location
	 *            The {@link Location} to spawn the particle at.
	 * @param world
	 *            The {@link World} to spawn the particle at.
	 * @param item
	 *            The {@link ItemStack} to be the base for this particle.
	 * @return A new Builder.
	 */
	public static Builder getBuilder(Location location, World world, ItemStack item) {
		return new Builder(location, world, item, new Random());
	}

	/**
	 * Builder for {@link ParticleItemCrack}.
	 */
	public static class Builder
	{
		private Location location;
		private World world;
		private int count;
		private ItemStack item;

		private Builder(Location location, World world, ItemStack item, Random rng) {
			this.location = location;
			this.world = world;
			this.count = rng.nextInt(IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS + 1) + IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS;
			this.item = item;
		}

		/**
		 * @return A new instance of {@link ParticleItemCrack} based on the Builder's
		 *         fields.
		 */
		public ParticleItemCrack build() {
			return new ParticleItemCrack(this.location, this.world, this.count, this.item);
		}

		/**
		 * Set the amount of particles to spawn.
		 * 
		 * @param count
		 *            The amount of particles to spawn.
		 * @return This {@link Builder}.
		 */
		public Builder setCount(int count) {
			this.count = count;
			return this;
		}
	}

	/**
	 * @return the {@link ItemStack} that is the base of this particular particle.
	 */
	public ItemStack getItem() {
		return item;
	}
}
