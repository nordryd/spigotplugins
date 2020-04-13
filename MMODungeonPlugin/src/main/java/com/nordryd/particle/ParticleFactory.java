package com.nordryd.particle;

import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;

import com.nordryd.enums.ColorEnumHandler.ParticleColor;
import com.nordryd.util.IValues;

/**
 * <p>
 * Class for handling all the particles generated by the plugin.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleFactory
{
	/**
	 * <p>
	 * Spawn a given set of {@link AbstractParticle}s. One or more can be given (yay
	 * ellipses *<i>peepohype</i>*).
	 * </p>
	 * 
	 * @param particles
	 *            The particles to spawn in terms of the plugin's
	 *            {@link AbstractParticle} class.
	 */
	public static void spawnParticles(final AbstractParticle... particles) {
		for (AbstractParticle particle : particles) {
			if (particle instanceof ParticleSparkle) {
				sparkle(((ParticleSparkle) particle));
			}
			else if (particle instanceof ParticleDust) {
				dust(((ParticleDust) particle));
			}
			else if (particle instanceof ParticleSpellEffect) {
				spellEffect(((ParticleSpellEffect) particle));
			}
			else if (particle instanceof ParticleEnchanting) {
				enchanting(((ParticleEnchanting) particle));
			}
			else if (particle instanceof ParticleDragonBreath) {
				dragonBreath(((ParticleDragonBreath) particle));
			}
			else if (particle instanceof ParticleItemCrack) {
				itemCrack(((ParticleItemCrack) particle));
			}
			else if (particle instanceof ParticleFlame) {
				flame(((ParticleFlame) particle));
			}
			else {
				System.err.println(IValues.PREFIX + " Somehow an invalid particle type was given? o.O");
			}
		}
	}

	private static void sparkle(final ParticleSparkle pSparkle) {
		pSparkle.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, pSparkle.getLocation(), pSparkle.getCount());
	}

	private static void dust(final ParticleDust pDust) {
		pDust.getWorld().spawnParticle(Particle.REDSTONE, pDust.getLocation(), pDust.getCount(), new DustOptions(pDust.getColor(), pDust.getSize()));

	}

	private static void spellEffect(final ParticleSpellEffect pSpellEffect) {
		for (int count = 0; count < pSpellEffect.getCount(); count++) {
			ParticleColor color = pSpellEffect.getColor();
			pSpellEffect.getWorld().spawnParticle(Particle.SPELL_MOB, pSpellEffect.getLocation(), 0, color.getNormalizedRed(),
					color.getNormalizedGreen(), color.getNormalizedBlue(), 1);
		}

	}

	private static void enchanting(final ParticleEnchanting pEnchanting) {
		pEnchanting.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, pEnchanting.getLocation(), pEnchanting.getCount());
	}

	private static void dragonBreath(final ParticleDragonBreath pDragonBreath) {
		pDragonBreath.getWorld().spawnParticle(Particle.DRAGON_BREATH, pDragonBreath.getLocation(), pDragonBreath.getCount());
	}

	private static void itemCrack(final ParticleItemCrack pItemCrack) {
		pItemCrack.getWorld().spawnParticle(Particle.ITEM_CRACK, pItemCrack.getLocation(), pItemCrack.getCount(), pItemCrack.getItem());
	}

	private static void flame(final ParticleFlame pFlame) {
		pFlame.getWorld().playEffect(pFlame.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
	}
}