package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;

import com.nordryd.enums.ColorEnumHandler.ParticleColor;
import com.nordryd.util.IValues;

/**
 * <p>
 * Class for <b><i>dust</i></b> particles with certain characteristics.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleDust extends AbstractParticle
{
	private final int size;
	private final Color color;

	private ParticleDust(Location location, World world, int count, int size, Color color) {
		super(location, world, count);
		this.size = size;
		this.color = color;
	}

	/**
	 * Get a {@link Builder} to get an instance of {@link ParticleDust}.
	 * 
	 * @param location
	 *            The {@link Location} to spawn the particle at.
	 * @param world
	 *            The {@link World} to spawn the particle at.
	 * @return A new Builder.
	 */
	public static Builder getBuilder(Location location, World world) {
		return new Builder(location, world, new Random());
	}

	/**
	 * Builder for {@link ParticleDust}.
	 */
	public static class Builder
	{
		private Location location;
		private World world;
		private int count, size;
		private Color color;

		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = rng.nextInt(IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS + 1) + IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS;
			this.size = rng.nextInt(IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS + 1) + IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS;
			this.color = ParticleColor.values()[rng.nextInt(ParticleColor.values().length)].getColorFromRGB();
		}

		/**
		 * @return A new instance of {@link ParticleDust} based on the Builder's fields.
		 */
		public ParticleDust build() {
			return new ParticleDust(this.location, this.world, this.count, this.size, this.color);
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

		/**
		 * Set size of the dust cloud(s).
		 * 
		 * @param size
		 *            The size of the dust cloud(s).
		 * @return This {@link Builder}.
		 */
		public Builder setSize(int size) {
			this.size = size;
			return this;
		}

		/**
		 * Set the color of the dust cloud(s).
		 * 
		 * @param color
		 *            The desired color of the dust cloud(s).
		 * @return This {@link Builder}.
		 */
		public Builder setColor(ParticleColor color) {
			this.color = color.getColorFromRGB();
			return this;
		}
	}

	/**
	 * @return The size of the dust cloud(s).
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return The color of the dust cloud(s).
	 */
	public Color getColor() {
		return color;
	}
}
