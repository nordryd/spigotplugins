package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.enums.ParticleColor;
import com.nordryd.util.Values.ParticleValues;

/**
 * Class for a <b><i>dust</i></b> particles with certain characteristics. Based
 * on <b>REDSTONE</b> particles.
 * 
 * @author Nordryd
 */
public class ParticleDust extends PluginParticle
{
	private final int size;
	private final Color color;

	private ParticleDust(Location location, World world, int count, int size, Color color) {
		super(Particle.REDSTONE, location, world, count);
		this.size = size;
		this.color = color;
	}

	public static Builder getBuilder(Location location, World world) {
		return new Builder(location, world, new Random());
	}

	public static class Builder
	{
		private Location location;
		private World world;
		private int count, size;
		private Color color;

		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = ParticleValues.PARTICLE_COUNT[rng.nextInt(ParticleValues.PARTICLE_COUNT.length)];
			this.size = rng.nextInt(10) + 1;
			this.color = ParticleColor.RED.getColorFromRGB();
		}

		public ParticleDust build() {
			return new ParticleDust(this.location, this.world, this.count, this.size, this.color);
		}

		public Builder setCount(int count) {
			this.count = count;
			return this;
		}

		public Builder setSize(int size) {
			this.size = size;
			return this;
		}

		public Builder setColor(ParticleColor color) {
			this.color = color.getColorFromRGB();
			return this;
		}
	}
	
	public int getSize() {
		return size;
	}

	public Color getColor() {
		return color;
	}
}
