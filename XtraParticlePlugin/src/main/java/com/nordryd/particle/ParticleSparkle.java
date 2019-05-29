package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.util.Values.ParticleValues;

/**
 * <p>
 * Class for <b><i>sparkle</i></b> particles with certain characteristics. Based
 * on <b>FIREWORK_SPARK</b> particles.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleSparkle extends PluginParticle
{
	private ParticleSparkle(Location location, World world, int count) {
		super(Particle.FIREWORKS_SPARK, location, world, count);
	}

	public static Builder getBuilder(Location location, World world) {
		return new Builder(location, world, new Random());
	}

	public static class Builder
	{
		private Location location;
		private World world;
		private int count;

		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = ParticleValues.PARTICLE_COUNT[rng.nextInt(ParticleValues.PARTICLE_COUNT.length)];
		}

		public ParticleSparkle build() {
			return new ParticleSparkle(this.location, this.world, this.count);
		}

		public Builder setCount(int count) {
			this.count = count;
			return this;
		}
	}
}
