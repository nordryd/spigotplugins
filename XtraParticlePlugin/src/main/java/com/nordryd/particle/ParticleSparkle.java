package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.util.Values.ParticleValues;

public class ParticleSparkle
{
	private final Particle particle;
	private final Location location;
	private final World world;
	private final int count;

	private ParticleSparkle(Location location, World world, int count) {
		this.particle = Particle.FIREWORKS_SPARK;
		this.location = location;
		this.world = world;
		this.count = count;
	}

	public static class Builder
	{
		private Location location;
		private World world;
		private int count;

		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = ParticleValues.SPARKLE_COUNT[rng.nextInt(ParticleValues.SPARKLE_COUNT.length)];
		}

		public static Builder builder(Location location, World world) {
			return new Builder(location, world, new Random());
		}

		public ParticleSparkle build() {
			return new ParticleSparkle(this.location, this.world, this.count);
		}

		public Builder setCount(int count) {
			this.count = count;
			return this;
		}
	}

	public Particle getParticle() {
		return particle;
	}

	public Location getLocation() {
		return location;
	}
	
	public World getWorld() {
		return world;
	}

	public int getCount() {
		return count;
	}

}
