package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.nordryd.util.Values;
import com.nordryd.util.Values.Config;

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
	
	public void sparkle(final ParticleSparkle pSparkle) {
		if (config.getBoolean(Config.DO_SPARKLES)) {
			Bukkit.broadcastMessage(Values.PREFIX + " Sparkle " + pSparkle.getCount());
			pSparkle.getWorld().spawnParticle(pSparkle.getParticle(), pSparkle.getLocation(), pSparkle.getCount());
		}
	}
	
	public void dust(final ParticleDust pDust) {
		if(config.getBoolean(Config.DO_DUST)) {
			Bukkit.broadcastMessage(Values.PREFIX + " Dust " + pDust.getCount() + ", " + pDust.getSize());
		}
	}
}
