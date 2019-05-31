package com.nordryd.particle;

import org.bukkit.Particle.DustOptions;
import org.bukkit.configuration.file.FileConfiguration;

import com.nordryd.enums.Config;
import com.nordryd.enums.ParticleColor;
import com.nordryd.util.Values;

/**
 * <p>
 * Class for handling all the particles generated by the plugin.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleHandler
{
	private final FileConfiguration config;

	/**
	 * Constructor.
	 * 
	 * @param pluginConfig
	 *            Plugin configuration.
	 */
	public ParticleHandler(FileConfiguration pluginConfig) {
		this.config = pluginConfig;
	}

	/**
	 * Spawn a given set of particles. One or more can be given (yay ellipses
	 * *<i>peepohype</i>*). Pseudo-factory?
	 * 
	 * @param particles
	 *            The particles to spawn in terms of the plugin's
	 *            {@code PluginParticle} class.
	 */
	public void spawnParticles(final PluginParticle... particles) {
		for (PluginParticle particle : particles) {
			if (particle instanceof ParticleSparkle) {
				sparkle((ParticleSparkle) particle);
			}
			else if (particle instanceof ParticleDust) {
				dust((ParticleDust) particle);
			}
			else if (particle instanceof ParticleSpellEffect) {
				spellEffect((ParticleSpellEffect) particle);
			}
			else if (particle instanceof ParticleEnchanting) {
				enchanting((ParticleEnchanting) particle);
			}
			else if (particle instanceof ParticleDragonBreath) {
				dragonBreath((ParticleDragonBreath) particle);
			}
			else {
				System.err.println(Values.PREFIX + " Somehow an invalid particle type was given? o.O");
			}
		}
	}

	private void sparkle(final ParticleSparkle pSparkle) {
		if (config.getBoolean(Config.DO_SPARKLE.getString())) {

			pSparkle.getWorld().spawnParticle(pSparkle.getParticle(), pSparkle.getLocation(), pSparkle.getCount());
		}
	}

	private void dust(final ParticleDust pDust) {
		if (config.getBoolean(Config.DO_DUST.getString())) {

			pDust.getWorld().spawnParticle(pDust.getParticle(), pDust.getLocation(), pDust.getCount(),
					new DustOptions(pDust.getColor(), pDust.getSize()));
		}
	}

	private void spellEffect(final ParticleSpellEffect pSpellEffect) {
		if (config.getBoolean(Config.DO_SPELLEFFECT.getString())) {
			for (int count = 0; count < pSpellEffect.getCount(); count++) {
				ParticleColor color = pSpellEffect.getColor();
				pSpellEffect.getWorld().spawnParticle(pSpellEffect.getParticle(), pSpellEffect.getLocation(), 0, color.getNormalizedRed(),
						color.getNormalizedGreen(), color.getNormalizedBlue(), 1);
			}
		}
	}

	private void enchanting(final ParticleEnchanting pEnchanting) {
		if (config.getBoolean(Config.DO_ENCHANTING.getString())) {
			pEnchanting.getWorld().spawnParticle(pEnchanting.getParticle(), pEnchanting.getLocation(), pEnchanting.getCount());
		}
	}

	private void dragonBreath(final ParticleDragonBreath pDragonBreath) {
		if (config.getBoolean(Config.DO_DRAGONBREATH.getString())) {
			pDragonBreath.getWorld().spawnParticle(pDragonBreath.getParticle(), pDragonBreath.getLocation(), pDragonBreath.getCount());
		}
	}
}
