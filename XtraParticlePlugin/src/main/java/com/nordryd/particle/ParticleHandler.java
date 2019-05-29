package com.nordryd.particle;

import org.bukkit.Bukkit;
import org.bukkit.Particle.DustOptions;
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

	/**
	 * Constructor.
	 * 
	 * @param pluginConfig
	 *            Plugin configuration.
	 */
	public ParticleHandler(FileConfiguration pluginConfig) {
		this.config = pluginConfig;
	}
	
	public void spawnParticles(final PluginParticle particle) {
		if(particle instanceof ParticleSparkle) {
			sparkle((ParticleSparkle)particle);
		}
		else if(particle instanceof ParticleDust) {
			dust((ParticleDust)particle);
		}
		else if(particle instanceof ParticleSpellEffect) {
			spellEffect((ParticleSpellEffect)particle);
		}
		else {
			System.err.println(Values.PREFIX + " Somehow an invalid particle type was given? o.O");
		}
	}

	private void sparkle(final ParticleSparkle pSparkle) {
		if (config.getBoolean(Config.DO_SPARKLES)) {
			Bukkit.broadcastMessage(Values.PREFIX + " Sparkle " + pSparkle.getCount());

			pSparkle.getWorld().spawnParticle(pSparkle.getParticle(), pSparkle.getLocation(), pSparkle.getCount());
		}
	}

	private void dust(final ParticleDust pDust) {
		if (config.getBoolean(Config.DO_DUST)) {
			Bukkit.broadcastMessage(Values.PREFIX + " Dust " + pDust.getCount() + ", " + pDust.getSize() + ", " + pDust.getColor());

			pDust.getWorld().spawnParticle(pDust.getParticle(), pDust.getLocation(), pDust.getCount(),
					new DustOptions(pDust.getColor(), pDust.getSize()));
		}
	}

	private void spellEffect(final ParticleSpellEffect pSpellEffect) {
		if (config.getBoolean(Config.DO_SPELLEFFECT)) {
			Bukkit.broadcastMessage(Values.PREFIX + " SpellEffect " + pSpellEffect.getCount() + ", " + pSpellEffect.getColor());

			for (int count = 0; count < pSpellEffect.getCount(); count++) {
				pSpellEffect.getWorld().spawnParticle(pSpellEffect.getParticle(), pSpellEffect.getLocation(), 0,
						pSpellEffect.getColor().getNormalizedRed(), pSpellEffect.getColor().getNormalizedGreen(),
						pSpellEffect.getColor().getNormalizedBlue(), 1);
			}
		}
	}
}
