package com.nordryd.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.enums.ParticleColor;

/**
 * Class for a <b><i>dust</i></b> particles with certain characteristics. Based
 * on <b>REDSTONE</b> particles.
 * 
 * @author Nordryd
 */
public class ParticleDust
{
	private final Particle particle;
	private final Location location;
	private final World world;
	private final int count, size;
	private final ParticleColor color;

	private ParticleDust(Location location, World world, int count, int size, ParticleColor color) {
		this.particle = Particle.REDSTONE;
		this.location = location;
		this.world = world;
		this.count = count;
		this.size = size;
		this.color = color;
	}

	public static class Builder
	{
		private Location location;
		private World world;
		private int count, size;
		private ParticleColor color;
		
		private Builder(Location location, World world) {
			this.location = location;
			this.world = world;
			this.count = 5;
			this.size = 1;
			this.color = ParticleColor.RED;
		}
		
		public static Builder builder(Location location, World world) {
			return new Builder(location, world);
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

	public int getSize() {
		return size;
	}

	public ParticleColor getColor() {
		return color;
	}
}
