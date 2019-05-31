package com.nordryd.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

/**
 * <p>
 * Class to represent a particle handled by the plugin.
 * </p>
 * 
 * @author Nordryd
 */
public abstract class PluginParticle
{
	private final Particle particle;
	private final Location location;
	private final World world;
	private final int count;

	/**
	 * Constructor.
	 * @param particle
	 * 			{@code Particle} type to spawn.
	 * @param location
	 * 			{@code Location} at which to particle.
	 * @param world
	 * 			{@code World} in which to spawn particle.
	 * @param count
	 * 			The amount of particles.
	 */
	protected PluginParticle(Particle particle, Location location, World world, int count) {
		this.particle = particle;
		this.location = location;
		this.world = world;
		this.count = count;
	}

	/**
	 * @return the {@code Particle} type.
	 */
	public Particle getParticle() {
		return particle;
	}

	/**
	 * @return the {@code Location} at which the particle is being spawned.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the {@code World} in which the particle is being spawned.
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @return the amount of particles.
	 */
	public int getCount() {
		return count;
	}
}
