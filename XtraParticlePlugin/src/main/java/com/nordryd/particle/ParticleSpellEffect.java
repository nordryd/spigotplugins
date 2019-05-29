package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.enums.ParticleColor;
import com.nordryd.util.Values.ParticleValues;

public class ParticleSpellEffect extends PluginParticle
{
	private ParticleColor color;

	public ParticleSpellEffect(Location location, World world, int count, ParticleColor color) {
		super(Particle.SPELL_MOB, location, world, count);
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
	
	public ParticleColor getColor() {
		return color;
	}
}
