package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * <p>
 * Class for <b><i>flame</i></b> particles with certain characteristics.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleFlame extends AbstractParticle
{
	private ParticleFlame(Location location, World world) {
		super(location, world, 0);
	}

	/**
	 * Get a {@link Builder} to get an instance of {@link ParticleFlame}.
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
	 * Builder for {@link ParticleFlame}.
	 */
	public static class Builder
	{
		private Location location;
		private World world;

		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
		}

		/**
		 * @return A new instance of {@link ParticleFlame} based on the Builder's
		 *         fields.
		 */
		public ParticleFlame build() {
			return new ParticleFlame(this.location, this.world);
		}
	}
}
