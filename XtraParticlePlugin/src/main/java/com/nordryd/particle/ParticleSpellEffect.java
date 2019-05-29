package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.enums.ParticleColor;
import com.nordryd.util.Values.ParticleValues;

public class ParticleSpellEffect
{
	private Particle particle = Particle.SPELL_MOB;
	private Location location;
	private World world;
	private int count;
	private ParticleColor color;

	public ParticleSpellEffect(Location location, World world, int count, ParticleColor color) {
		this.location = location;
		this.world = world;
		this.count = count;
		this.color = color;
	}

	public static Builder getBuilder(Location location, World world) {
		return new Builder(location, world, new Random());
	}

	public static class Builder
	{
		private Location location;
		private World world;
		private int count;
		private ParticleColor color;

		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = ParticleValues.PARTICLE_COUNT[rng.nextInt(ParticleValues.PARTICLE_COUNT.length)];
			this.color = ParticleColor.WHITE;
		}

		public ParticleSpellEffect build() {
			return new ParticleSpellEffect(this.location, this.world, this.count, this.color);
		}

		public Builder setCount(int count) {
			this.count = count;
			return this;
		}
		
		public Builder setColor(ParticleColor color) {
			this.color = color;
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
	
	public ParticleColor getColor() {
		return color;
	}
}
