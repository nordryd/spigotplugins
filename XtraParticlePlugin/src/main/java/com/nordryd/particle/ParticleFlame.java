package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.util.IValues;

public class ParticleFlame extends AbstractParticle
{
	private ParticleFlame(Location location, World world, int count) {
		super(Particle.FLAME, location, world, count);
	}
	
	public static Builder getBuilder(Location location, World world) {
		return new Builder(location, world, new Random());
	}
	
	public static class Builder{
		private Location location;
		private World world;
		private int count;
		
		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = (rng.nextInt(IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS) + 1) * IValues.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS;
		}
		public ParticleFlame build() {
			return new ParticleFlame(this.location, this.world, this.count);
		}
		
		public Builder setCount(int count) {
			this.count = count;
			return this;
		}
	}
}
