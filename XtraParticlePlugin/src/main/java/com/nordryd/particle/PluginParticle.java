package com.nordryd.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public abstract class PluginParticle
{
	private Particle particle;
	private Location location;
	private World world;
	private int count;
	
	protected PluginParticle(Particle particle, Location location, World world, int count) {
		this.particle = particle;
		this.location = location;
		this.world = world;
		this.count = count;
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
