package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;

import com.nordryd.util.IValues;

/**
 * <p>
 * Class for <b><i>dragon breath</i></b> particles with certain characteristics.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleDragonBreath extends AbstractParticle
{
	private ParticleDragonBreath(Location location, World world, int count) {
		super(location, world, count);
	}
	
	/**
	 * Get a {@link Builder} to get an instance of {@link ParticleDragonBreath}.
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
	 * Builder for {@link ParticleDragonBreath}.
	 */
	public static class Builder{
		private Location location;
		private World world;
		private int count;
		
		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = (rng.nextInt(IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS) + 1) * IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS;
		}
		
		/**
		 * @return A new instance of {@link ParticleDragonBreath} based on the Builder's fields.
		 */
		public ParticleDragonBreath build() {
			return new ParticleDragonBreath(this.location, this.world, this.count);
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
}
