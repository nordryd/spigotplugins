package com.nordryd.sparkle;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

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
	private final Random rand;

	/**
	 * Constructor.
	 * 
	 * @param pluginConfig
	 *            Plugin configuration.
	 */
	public ParticleHandler(FileConfiguration pluginConfig) {
		this.config = pluginConfig;
		this.rand = new Random();
	}

	/**
	 * Creates <b><i>sparkles</i></b> at a given location. The radius will be
	 * random.
	 * 
	 * @param location
	 *            {@code Location} at which to create sparkles.
	 * @param world
	 *            {@code World} in which to create sparkles.
	 */
	public void sparkle(Location location, World world) {
		sparkle(location, world, getRandomSparkleCount());
	}

	/**
	 * Creates <b><i>sparkles</i></b> at a given location.
	 * 
	 * @param location
	 *            {@code Location} at which to create sparkles.
	 * @param world
	 *            {@code World} in which to create sparkles.
	 * @param radius
	 *            The size of the sparkles.
	 */
	public void sparkle(Location location, World world, int radius) {
		if (config.getBoolean(Config.DO_SPARKLES)) {
			Bukkit.broadcastMessage(Values.PREFIX + " Sparkle " + radius);
			world.spawnParticle(Particle.FIREWORKS_SPARK, location, radius);
		}
	}

	private int getRandomSparkleCount() {
		return ParticleValues.SPARKLE_COUNT[rand.nextInt(ParticleValues.SPARKLE_COUNT.length)];
	}
}
