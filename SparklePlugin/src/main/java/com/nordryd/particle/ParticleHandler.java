package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import com.nordryd.enums.ParticleColor;
import com.nordryd.util.Values;
import com.nordryd.util.Values.Config;
import com.nordryd.util.Values.ParticleValues;

/**
 * Class for handling all the extra particles in the plugin.
 * 
 * @author Nordryd
 */
public class ParticleHandler
{
	private final FileConfiguration config;
	private final Random rng;

	/**
	 * Constructor.
	 * 
	 * @param pluginConfig
	 *            Plugin configuration.
	 */
	public ParticleHandler(FileConfiguration pluginConfig) {
		this.config = pluginConfig;
		this.rng = new Random();
	}

	/**
	 * Spawns <b><i>sparkles</i></b> at a given location. The <i>count of
	 * particles</i> will be random.
	 * 
	 * @param location
	 *            {@code Location} at which to spawn sparkles.
	 * @param world
	 *            {@code World} in which to spawn sparkles.
	 */
	public void sparkle(final Location location, final World world) {
		sparkle(location, world, getRandomSparkleCount());
	}

	/**
	 * Spawns <b><i>sparkles</i></b> at a given location.
	 * 
	 * @param location
	 *            {@code Location} at which to spawn sparkles.
	 * @param world
	 *            {@code World} in which to spawn sparkles.
	 * @param count
	 *            The number of particles that will spawn.
	 */
	public void sparkle(final Location location, final World world, final int count) {
		if (config.getBoolean(Config.DO_SPARKLES)) {
			Bukkit.broadcastMessage(Values.PREFIX + " Sparkle " + count);
			world.spawnParticle(Particle.REDSTONE, location, count);
		}
	}
	
	public void dust(final Dust dust) {
		
	}

	private int getRandomSparkleCount() {
		return ParticleValues.SPARKLE_COUNT[rng.nextInt(ParticleValues.SPARKLE_COUNT.length)];
	}

	private ParticleColor getRandomParticleColor() {
		return ParticleColor.values()[rng.nextInt(ParticleColor.values().length)];
	}
}
