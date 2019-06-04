package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.util.IValues;

/**
 * <p>
 * Class for <b><i>sparkle</i></b> particles with certain characteristics. Based
 * on <b>FIREWORK_SPARK</b> particles.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleSparkle extends AbstractParticle
{
	private ParticleSparkle(Location location, World world, int count) {
		super(Particle.FIREWORKS_SPARK, location, world, count);
	}

	/**
	 * Get a {@code Builder} to get an instance of {@code ParticleSparkle}.
	 * 
	 * @param location
	 *            The {@code Location} to spawn the particle at.
	 * @param world
	 *            The {@code World} to spawn the particle at.
	 * @return A new Builder.
	 */
	public static Builder getBuilder(Location location, World world) {
		return new Builder(location, world, new Random());
	}

	/**
	 * Builder for {@code ParticleSparkle}.
	 */
	public static class Builder
	{
		private Location location;
		private World world;
		private int count;

		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = (rng.nextInt(IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS) + 1) * IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS;
		}

		/**
		 * @return A new instance of {@code ParticleSparkle} based on the Builder's
		 *         fields.
		 */
		public ParticleSparkle build() {
			return new ParticleSparkle(this.location, this.world, this.count);
		}
		
		/**
		 * Set the amount of particles to spawn.
		 * @param count
		 * 			The amount of particles to spawn.
		 * @return
		 * 			This {@code Builder}.
		 */
		public Builder setCount(int count) {
			this.count = count;
			return this;
		}
	}
}
