package com.nordryd.particle;

import org.bukkit.Location;
import org.bukkit.Particle;

import com.nordryd.enums.ParticleColor;

/**
 * Class for a <b><i>dust</i></b> particles with certain characteristics. Based
 * on <b>REDSTONE</b> particles.
 * 
 * @author Nordryd
 */
public class Dust
{
	private final Particle particle;
	private final Location location;
	private final int count, size;
	private final ParticleColor color;

	private Dust(Location location, int count, int size, ParticleColor color) {
		this.particle = Particle.REDSTONE;
		this.location = location;
		this.count = count;
		this.size = size;
		this.color = color;
	}

	public Location getLocation() {
		return location;
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
